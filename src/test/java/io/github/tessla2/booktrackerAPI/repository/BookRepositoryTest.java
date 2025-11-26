package io.github.tessla2.booktrackerAPI.repository;


import io.github.tessla2.booktrackerAPI.model.Author;
import io.github.tessla2.booktrackerAPI.model.Book;
import io.github.tessla2.booktrackerAPI.model.Genre;
import io.github.tessla2.booktrackerAPI.model.Status;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest
public class BookRepositoryTest {

    @Autowired
    BookRepository repository;

    @Autowired
    AuthorRepository authorRepository;

    @Test
    void saveTest(){
        Book book = new Book();
        book.setTitle("Teste");
        book.setGenre(Genre.CRIME);
        book.setTotalPages(1000);
        book.setStatus(Status.ON_LIST);
        book.setStartDate(LocalDate.of(2025, 11, 24));
        Author author = authorRepository
                .findById(UUID.fromString("2f0f141c-d548-401f-8142-32f1314f12a4"))
                .orElse(null);

        book.setAuthor(author);

        repository.save(book);
    }


    @Test
    void saveCascadeTest() {

        Book book = new Book();
        book.setTitle("Teste");
        book.setGenre(Genre.CRIME);
        book.setTotalPages(1000);
        book.setStatus(Status.ON_LIST);
        book.setStartDate(LocalDate.of(2025, 11, 24));

        Author author = new Author();
        author.setName("Teste");
        author.setNationality("Test");

        book.setAuthor(author);

        repository.save(book); // Cascade saves author too ✅
    }







}
