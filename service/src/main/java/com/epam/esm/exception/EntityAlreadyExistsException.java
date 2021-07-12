package com.epam.esm.exception;

/**
 * Exception for entity already exists
 */
public class EntityAlreadyExistsException extends RuntimeException {

    private final String MESSAGE;

    /**
     * Constructor
     *
     * @param message the message
     */
    public EntityAlreadyExistsException(String message) {

        this.MESSAGE = message;
    }

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
