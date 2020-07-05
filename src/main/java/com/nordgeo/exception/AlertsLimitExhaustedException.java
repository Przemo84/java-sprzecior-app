package com.nordgeo.exception;

public class AlertsLimitExhaustedException extends RuntimeException {
    public AlertsLimitExhaustedException(String s) {
        super(s);
    }
}
