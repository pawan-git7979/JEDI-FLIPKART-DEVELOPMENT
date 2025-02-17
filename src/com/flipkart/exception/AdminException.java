package com.flipkart.exception;

public class AdminException extends Exception {
    // Constructor for general exception message
    public AdminException(String message) {
        super(message);
    }

    // Constructor for exception with a cause
    public AdminException(String message, Throwable cause) {
        super(message, cause);
    }
}
