package com.epam.esm.exception;

/**
 * Exception to throw by security
 */
public class JwtAuthenticationException extends RuntimeException {

    private final String MESSAGE;

    /**
     * Instantiates a new Jwt authentication exception.
     *
     * @param msg the msg
     */
    public JwtAuthenticationException(String msg) {
        this.MESSAGE = msg;
    }

    @Override
    public String getMessage() {
        return MESSAGE;
    }
}
