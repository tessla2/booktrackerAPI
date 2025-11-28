package io.github.tessla2.booktrackerAPI.repository;

import io.github.tessla2.booktrackerAPI.model.Author;
import io.github.tessla2.booktrackerAPI.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID>, JpaSpecificationExecutor<Book>{


    boolean existsByAuthor(Author author);
}
