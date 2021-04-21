package com.epam.esm.controller;

import com.epam.esm.domain.Error;
import com.epam.esm.exception.EntityAlreadyExistsException;
import com.epam.esm.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {

    @ResponseBody
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Error entityNotFound(EntityNotFoundException ex) {
        String message = ex.getMessage();
        return new Error(404, message);
    }

    /**
     * Exception handler to catch while tag already exists
     *
     * @param ex the ex
     * @return the error
     */
    @ResponseBody
    @ExceptionHandler(EntityAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public Error entityAlreadyExists(EntityAlreadyExistsException ex) {
        String message = ex.getMessage();
        return new Error(409, message);
    }
}
