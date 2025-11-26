package io.github.tessla2.booktrackerAPI.exceptions;

import io.github.tessla2.booktrackerAPI.controller.dto.InvalidField;
import lombok.Getter;

public class InvalidComponentException extends RuntimeException{

    @Getter
    private String field;




    public InvalidComponentException(String field, String message){
        super(message);
        this.field = field;
    }
}
