package com.epam.esm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class EntityNotFoundException extends RuntimeException {

    private String message;

    @Override
    public String getMessage() {
        return message;
    }

    public EntityNotFoundException(String message) {

        this.message = message;
    }

}
