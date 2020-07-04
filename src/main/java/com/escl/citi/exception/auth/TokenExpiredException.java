package com.escl.citi.exception.auth;

public class TokenExpiredException extends RuntimeException implements CodeException {

    private int code;

    public TokenExpiredException(String message) {
        super(message);
    }

    public TokenExpiredException(String message, int code) {
        super(message);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
