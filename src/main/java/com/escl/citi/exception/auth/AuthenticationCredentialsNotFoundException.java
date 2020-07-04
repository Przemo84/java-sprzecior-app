package com.escl.citi.exception.auth;

public class AuthenticationCredentialsNotFoundException extends org.springframework.security.authentication.AuthenticationCredentialsNotFoundException implements CodeException {

    private int code;

    public AuthenticationCredentialsNotFoundException(String msg) {
        super(msg);
    }

    public AuthenticationCredentialsNotFoundException(String msg, int code) {
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
