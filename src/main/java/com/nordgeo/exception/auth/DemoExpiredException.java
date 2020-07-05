package com.nordgeo.exception.auth;

public class DemoExpiredException extends org.springframework.security.authentication.CredentialsExpiredException implements CodeException {

    private int code;

    public DemoExpiredException(String msg) {
        super(msg);
    }

    public DemoExpiredException(String msg, int code) {
        super(msg);
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
