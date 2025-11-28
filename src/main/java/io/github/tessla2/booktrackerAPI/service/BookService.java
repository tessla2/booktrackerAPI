package io.github.tessla2.booktrackerAPI.service;


import io.github.tessla2.booktrackerAPI.mapper.BookMapper;
import io.github.tessla2.booktrackerAPI.model.Author;
import io.github.tessla2.booktrackerAPI.model.Book;
import io.github.tessla2.booktrackerAPI.model.Genre;
import io.github.tessla2.booktrackerAPI.model.Status;
import io.github.tessla2.booktrackerAPI.repository.BookRepository;
import io.github.tessla2.booktrackerAPI.validator.BookValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import static io.github.tessla2.booktrackerAPI.repository.specs.BookSpecs.*;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final BookValidator validator;
    private final BookMapper mapper;


    public Book save(Book book){
        Author author = validator.validateAuthor(book);
        book.setAuthor(author);
        validator.validate(book);
        return repository.save(book);
    }


    public Optional<Book> getById(UUID uuid) {
        return repository.findById(uuid);
    }

    public void delete (Book book){
        repository.delete(book);
    }

    public Page<Book> search(String title,
                             Genre genre,
                             String authorName,
                             String totalPages,
                             String currentPage,
                             String rating,
                             LocalDate endDate,
                             LocalDate startDate,
                             Status status,
                             Integer page,
                             Integer pageSize) {

        // conjunction = where 0 = 0
        Specification<Book> specs = ((root, query, cb) -> cb.conjunction());

        if(title != null){
            specs = specs.and(titleLike(title));
        }

         if(genre != null){
             specs = specs.and(genreEqual(genre));
         }

         if(status != null){
             specs = specs.and(statusEqual(status));
         }

         Pageable pageRequest = PageRequest.of(page, pageSize);

         return repository.findAll(specs, pageRequest);
    }

    public void update(Book book) {
        if(book.getId() == null){
            throw new IllegalArgumentException("Book cannot be null for update.");
        }
        repository.save(book);
    }
}
