package com.epam.esm.exception;

/**
 * Exception to throw by security
 */
public class JwtAuthenticationException extends RuntimeException {

    private String message;

    /**
     * Instantiates a new Jwt authentication exception.
     *
     * @param msg the msg
     */
    public JwtAuthenticationException(String msg) {
        this.message = msg;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
