package io.github.tessla2.booktrackerAPI.validator;


import io.github.tessla2.booktrackerAPI.exceptions.InvalidComponentException;
import io.github.tessla2.booktrackerAPI.model.Author;
import io.github.tessla2.booktrackerAPI.model.Book;
import io.github.tessla2.booktrackerAPI.repository.AuthorRepository;
import io.github.tessla2.booktrackerAPI.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookValidator {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;


    public void validate(Book book){
        if(book.getId() != null && bookRepository.existsById(book.getId())){
            throw new InvalidComponentException(book.getTitle(), "Book already exists.");
        }
    }


    public Author validateAuthor(Book book){
        if (book == null || book.getAuthor() == null || book.getAuthor().getId() == null) {
            throw new InvalidComponentException("author", "Author id is required.");
        }
        return authorRepository.findById(book.getAuthor().getId())
                .orElseThrow(() -> new InvalidComponentException("author", "Author not found."));
    }

    }


