package com.nordgeo.exception.auth;

public class CredentialsExpiredException extends org.springframework.security.authentication.CredentialsExpiredException implements CodeException {

    private int code;

    public CredentialsExpiredException(String msg) {
        super(msg);
    }

    public CredentialsExpiredException(String msg, int code) {
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
