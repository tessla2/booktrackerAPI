package io.github.tessla2.booktrackerAPI.controller.dto;

import io.github.tessla2.booktrackerAPI.model.Genre;
import io.github.tessla2.booktrackerAPI.model.Status;

import java.time.LocalDate;
import java.util.UUID;

public record SearchBookDTO (
    UUID id,
    String title,
    Genre genre,
    Integer totalPages,
    Integer currentPage,
    Integer rating,
    LocalDate startDate,
    LocalDate endDate,
    Status status,
    AuthorDTO author
)
{
}
