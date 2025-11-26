package io.github.tessla2.booktrackerAPI.service;


import io.github.tessla2.booktrackerAPI.controller.dto.BookRegistrationDTO;
import io.github.tessla2.booktrackerAPI.mapper.BookMapper;
import io.github.tessla2.booktrackerAPI.model.Author;
import io.github.tessla2.booktrackerAPI.model.Book;
import io.github.tessla2.booktrackerAPI.model.Genre;
import io.github.tessla2.booktrackerAPI.model.Status;
import io.github.tessla2.booktrackerAPI.repository.BookRepository;
import io.github.tessla2.booktrackerAPI.validator.BookValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

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






    }
}
