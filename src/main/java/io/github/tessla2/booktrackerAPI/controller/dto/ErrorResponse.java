package io.github.tessla2.booktrackerAPI.controller.dto;

import org.springframework.http.HttpStatus;

import java.util.List;

public record ErrorResponse(int status, String message, List<InvalidField> error) {


    // 404 - Not Found
    public static ErrorResponse standardError(String message){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), message, List.of());
    }

    // 409 - Conflict
    public static ErrorResponse ConflictError(String message){
        return new ErrorResponse(HttpStatus.CONFLICT.value(), message, List.of());
    }
}
