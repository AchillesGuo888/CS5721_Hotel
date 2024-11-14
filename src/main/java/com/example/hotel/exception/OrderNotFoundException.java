package com.example.hotel.exception;

public class OrderNotFoundException extends RuntimeException {

    // No-argument constructor
    public OrderNotFoundException() {
        super();
    }

    // Constructor with parameters, you can pass custom error messages
    public OrderNotFoundException(String message) {
        super(message);
    }

    // Constructor with parameters to pass error message and original exception
    public OrderNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
