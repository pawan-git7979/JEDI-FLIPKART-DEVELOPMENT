package com.flipkart.exception;

public class SlotNotAvailableException extends Exception {
    public SlotNotAvailableException(String message) {
        super(message);
    }

    public SlotNotAvailableException(String message, Throwable cause) {
        super(message, cause);
    }
}
