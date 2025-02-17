package com.flipkart.exception;

public class AuthException extends Exception {
    // Constructor for exception with a message
    public AuthException(String message) {
        super(message);
    }

    // Constructor for exception with both a message and a cause
    public AuthException(String message, Throwable cause) {
        super(message, cause);
    }
}
