package io.github.tessla2.booktrackerAPI.controller.dto;

import io.github.tessla2.booktrackerAPI.model.Genre;
import io.github.tessla2.booktrackerAPI.model.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record BookRegistrationDTO

    (
    @NotBlank(message = "Required field")
    String title,
    Genre genre,
    Integer totalPages,
    Integer currentPage,
    Integer rating,
    LocalDate endDate,
    LocalDate startDate,
    Status status,
    @NotNull(message = "Required field")
    UUID idAuthor
){
}
