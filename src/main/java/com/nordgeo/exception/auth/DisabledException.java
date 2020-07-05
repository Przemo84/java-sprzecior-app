package com.nordgeo.exception.auth;

public class DisabledException extends org.springframework.security.authentication.DisabledException implements CodeException {

    private Integer code;

    public DisabledException(String msg) {
        super(msg);
    }

    public DisabledException(String msg, Integer code) {
        super(msg);

        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
