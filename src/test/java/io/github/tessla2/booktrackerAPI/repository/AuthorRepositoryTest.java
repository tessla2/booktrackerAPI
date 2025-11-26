package io.github.tessla2.booktrackerAPI.repository;


import io.github.tessla2.booktrackerAPI.model.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
public class AuthorRepositoryTest {

    @Autowired
    AuthorRepository repository;

    @Test
    void saveAuthorTest(){
     Author author = new Author();
     author.setName("Test 2");
     author.setNationality("Test 2");

     repository.save(author);

    }


    @Test
    void deleteAuthorTest(){

        var id = UUID.fromString("abba2153-3b2e-4244-8b8f-00cd8e1aa882");
        repository.deleteById(id);

    }

}
