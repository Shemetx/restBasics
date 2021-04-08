package com.epam.esm.domain;

/**
 * Error class to return with exception to client
 */
public class Error {
    private int code;
    private String message;

    /**
     * Instantiates a new Error.
     *
     * @param code    the code
     * @param message the message
     */
    public Error(int code, String message) {
        this.code = code;
        this.message = message;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * Gets message.
     *
     * @return the message
     */
    public String getMessage() {
        return message;
    }
}
