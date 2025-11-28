package io.github.tessla2.booktrackerAPI.controller;


import io.github.tessla2.booktrackerAPI.controller.dto.BookRegistrationDTO;
import io.github.tessla2.booktrackerAPI.controller.dto.SearchBookDTO;
import io.github.tessla2.booktrackerAPI.mapper.BookMapper;
import io.github.tessla2.booktrackerAPI.model.Book;
import io.github.tessla2.booktrackerAPI.model.Genre;
import io.github.tessla2.booktrackerAPI.model.Status;
import io.github.tessla2.booktrackerAPI.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping("book-tracker")

@RequiredArgsConstructor
public class BookController implements GenericController {

    private final BookService service;

    private final BookMapper mapper;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid BookRegistrationDTO dto){
        Book book = mapper.toEntity(dto);
        service.save(book);
        var url = generateHeaderLocation(book.getId());
        return ResponseEntity.created(url).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<SearchBookDTO> getBooks(@PathVariable ("id") String id) {
        return service.getById(UUID.fromString(id))
                .map(book -> {
                    var dto = mapper.toDTO(book);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> delete(@PathVariable ("id") String id){
        return service.getById(UUID.fromString(id))
                .map(book -> {
                    service.delete(book);
                    return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Pageable search endpoint
    @GetMapping
    public ResponseEntity<Page<SearchBookDTO>> search(
            @RequestParam(value = "title", required = false) String title,
            @RequestParam(value = "genre", required = false) Genre genre,
            @RequestParam(value = "author-name", required = false) String authorName,
            @RequestParam(value = "total-pages", required = false) String totalPages,
            @RequestParam(value = "current-page", required = false) String currentPage,
            @RequestParam(value = "rating", required = false) String rating,
            @RequestParam(value = "end-date", required = false) String endDate,
            @RequestParam(value = "start-date", required = false) String startDate,
            @RequestParam(value = "status", required = false) Status status,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "page-size", defaultValue = "10") Integer pageSize
    ){
        Page<Book> pageResult = service.search(
                title,
                genre,
                authorName,
                totalPages,
                currentPage,
                rating,
                endDate != null ? LocalDate.parse(endDate) : null,
                startDate != null ? LocalDate.parse(startDate) : null,
                status,
                page,
                pageSize);

        Page<SearchBookDTO> result = pageResult.map(mapper::toDTO);
        return ResponseEntity.ok(result);
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> update(@PathVariable("id") String id,
                                         @RequestBody @Valid BookRegistrationDTO dto){
        return service.getById(UUID.fromString(id))
                .map(book -> {
                    Book entityAux = mapper.toEntity(dto);
                    book.setTitle(entityAux.getTitle());
                    book.setGenre(entityAux.getGenre());
                    book.setTotalPages(entityAux.getTotalPages());
                    book.setCurrentPage(entityAux.getCurrentPage());
                    book.setRating(entityAux.getRating());
                    book.setEndDate(entityAux.getEndDate());
                    book.setStartDate(entityAux.getStartDate());
                    book.setStatus(entityAux.getStatus());
                    service.update(book);

                    return ResponseEntity.noContent().build(); // no content on successful update
                }).orElseGet( () -> ResponseEntity.notFound().build());
    }
}
