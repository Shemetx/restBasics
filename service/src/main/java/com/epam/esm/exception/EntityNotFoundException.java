package com.epam.esm.exception;

/**
 * Exception for 404 error
 */
public class EntityNotFoundException extends RuntimeException {

    private String message;

    /**
     * Constructor
     *
     * @param message the message
     */
    public EntityNotFoundException(String message) {

        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
