package io.github.tessla2.booktrackerAPI.repository;


import io.github.tessla2.booktrackerAPI.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AuthorRepository extends JpaRepository<Author, UUID> {
}
