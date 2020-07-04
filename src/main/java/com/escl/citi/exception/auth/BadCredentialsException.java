package com.escl.citi.exception.auth;

public class BadCredentialsException extends org.springframework.security.authentication.BadCredentialsException implements CodeException {

    private Integer code;

    public BadCredentialsException(String msg) {
        super(msg);
    }

    public BadCredentialsException(String msg, Integer code) {
        super(msg);

        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
