package com.flipkart.exception;

public class CustomerException extends Exception {

    // Constructor for exception with a message
    public CustomerException(String message) {
        super(message);
    }

    // Constructor for exception with both a message and a cause
    public CustomerException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor for exception with a cause
    public CustomerException(Throwable cause) {
        super(cause);
    }
}
