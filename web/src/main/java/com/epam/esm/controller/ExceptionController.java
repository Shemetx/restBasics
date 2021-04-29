package com.epam.esm.controller;

import com.epam.esm.domain.Error;
import com.epam.esm.exception.EntityAlreadyExistsException;
import com.epam.esm.exception.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

/**
 * Catches exceptions that was thrown.
 */
@ControllerAdvice
public class ExceptionController {

    /**
     * Handle Entity not found error.
     *
     * @param ex the ex
     * @return the error
     */
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

    /**
     * Handle validation exception.
     *
     * @param ex the ex
     * @return the map
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> entityValidation(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    /**
     * Handle wrong url parameters error.
     *
     * @param ex the ex
     * @return the error
     */
    @ResponseBody
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public Error wrongUrlParameters(MethodArgumentTypeMismatchException ex) {
        return new Error(422, "Bad request check input entities");
    }

    /**
     * Handle Illegal arguments passed error.
     *
     * @param ex the ex
     * @return the error
     */
    @ResponseBody
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public Error illegalArgumentsPassed(IllegalArgumentException ex) {
        return new Error(422, ex.getMessage());
    }
}
