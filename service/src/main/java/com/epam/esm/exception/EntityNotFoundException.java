package com.epam.esm.exception;

/**
 * Exception for 404 error
 */
public class EntityNotFoundException extends RuntimeException {

    private final String MESSAGE;

    /**
     * Constructor
     *
     * @param message the message
     */
    public EntityNotFoundException(String message) {

        this.MESSAGE = message;
    }

    @Override
    public String getMessage() {
        return MESSAGE;
    }

}
