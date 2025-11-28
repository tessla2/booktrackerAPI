package io.github.tessla2.booktrackerAPI.service;


import io.github.tessla2.booktrackerAPI.model.Author;
import io.github.tessla2.booktrackerAPI.repository.AuthorRepository;
import io.github.tessla2.booktrackerAPI.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;


    public Author save(Author author){
        return authorRepository.save(author);
    }


    public Optional<Author> getFromId(UUID id) {
        return authorRepository.findById(id);
    }

    public void deleteAuthor(Author author) {
        if(hasBooks(author)){
            throw new IllegalStateException("Author with associated books cannot be deleted.");
        }
        authorRepository.delete(author);
    }

    private boolean hasBooks(Author author) {
        return bookRepository.existsByAuthor(author);
    }
}
