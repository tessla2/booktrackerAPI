package io.github.tessla2.booktrackerAPI.repository;

import io.github.tessla2.booktrackerAPI.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookRepository extends JpaRepository<Book, UUID> {



}
