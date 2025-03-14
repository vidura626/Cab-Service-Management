package com.example.cabservice.exceptions;
public class AlreadyAvailableException extends RuntimeException {
    public AlreadyAvailableException(String message) {
        super(message);
    }
}
