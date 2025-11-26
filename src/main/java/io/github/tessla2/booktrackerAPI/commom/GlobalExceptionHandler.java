package io.github.tessla2.booktrackerAPI.commom;

import io.github.tessla2.booktrackerAPI.controller.dto.ErrorResponse;
import io.github.tessla2.booktrackerAPI.controller.dto.InvalidField;
import io.github.tessla2.booktrackerAPI.exceptions.InvalidComponentException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
/**
 * Global exception handler for the REST API.
 * Captures exceptions thrown by controllers and returns
 * standardized JSON responses, avoiding code duplication for error handling.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException e){
        List<FieldError> fieldErrors = e.getFieldErrors();
        List<InvalidField> errorsList = fieldErrors
                .stream()
                .map(fe -> new InvalidField(fe.getField(), fe.getDefaultMessage()))
                .collect(Collectors.toList());

        return new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Validation Error. ",
                errorsList);
    }


    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    @ExceptionHandler
    public ErrorResponse handleInvalidComponentException(InvalidComponentException e){
        return new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Error in component validation.",
                List.of(new InvalidField(e.getField(), e.getMessage()))
                );
    }




}
