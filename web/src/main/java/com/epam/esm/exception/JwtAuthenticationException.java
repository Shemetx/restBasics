package com.epam.esm.exception;

public class JwtAuthenticationException extends RuntimeException {

    private String message;

    public JwtAuthenticationException(String msg) {
        this.message = msg;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
