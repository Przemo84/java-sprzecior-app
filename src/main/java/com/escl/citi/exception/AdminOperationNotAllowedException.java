package com.escl.citi.exception;

public class AdminOperationNotAllowedException extends RuntimeException {

    public AdminOperationNotAllowedException(String message) {
        super(message);
    }
}
