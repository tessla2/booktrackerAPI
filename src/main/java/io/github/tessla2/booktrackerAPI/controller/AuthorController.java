package io.github.tessla2.booktrackerAPI.controller;


import io.github.tessla2.booktrackerAPI.controller.dto.AuthorDTO;
import io.github.tessla2.booktrackerAPI.mapper.AuthorMapper;
import io.github.tessla2.booktrackerAPI.model.Author;
import io.github.tessla2.booktrackerAPI.repository.AuthorRepository;
import io.github.tessla2.booktrackerAPI.service.AuthorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("author-tracker")
public class AuthorController implements GenericController{

    private final AuthorRepository repository;
    private final AuthorService service;
    private final AuthorMapper mapper;

    @PostMapping
    public ResponseEntity<Void> save(@RequestBody @Valid AuthorDTO dto){ //RequestBody to convert JSON
        Author author = mapper.toEntity(dto);
        repository.save(author);

        var url = generateHeaderLocation(author.getId());
        return ResponseEntity.created(url).build();
    }

    @GetMapping("{id}")
    public ResponseEntity<AuthorDTO> getDetails(@PathVariable("id") String id){
        var idAuthor = UUID.fromString(id);

        return service
                .getFromId(idAuthor)
                .map(author -> {
                    AuthorDTO dto = mapper.toDTO(author);
                    return ResponseEntity.ok(dto);
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> deleteAuthor(@PathVariable ("id") String id){
        var idAuthor = UUID.fromString(id);
        Optional<Author> authorOptional = service.getFromId(idAuthor);

        if(authorOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else
            service.deleteAuthor(authorOptional.get());;
            return ResponseEntity.noContent().build();
        }
}
