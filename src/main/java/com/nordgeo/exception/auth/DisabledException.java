package com.nordgeo.exception.auth;

public class DisabledException extends org.springframework.security.authentication.DisabledException {

    public DisabledException(String msg) { super(msg); }

}
