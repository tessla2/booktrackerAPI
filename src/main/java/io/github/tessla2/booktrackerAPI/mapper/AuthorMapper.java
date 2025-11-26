package io.github.tessla2.booktrackerAPI.mapper;

import io.github.tessla2.booktrackerAPI.controller.dto.AuthorDTO;
import io.github.tessla2.booktrackerAPI.model.Author;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AuthorMapper {
    Author toEntity(AuthorDTO dto);

    AuthorDTO toDTO(Author author);



}
