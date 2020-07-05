package com.nordgeo.exception;

public class AdminOperationNotAllowedException extends RuntimeException {

    public AdminOperationNotAllowedException(String message) {
        super(message);
    }
}
