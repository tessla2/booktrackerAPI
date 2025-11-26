package io.github.tessla2.booktrackerAPI.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

public record AuthorDTO (
    UUID id,

    @NotBlank(message = "Required field")
    @Size(min = 2, max = 100, message = "Field exceeds the maximum allowed length")
    String name,

    @Past(message = "Birth date cannot be in the future dummy.")
    LocalDate birthDate,

    String nationality
) {
}
