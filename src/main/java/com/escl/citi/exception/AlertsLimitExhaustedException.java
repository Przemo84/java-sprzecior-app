package com.escl.citi.exception;

public class AlertsLimitExhaustedException extends RuntimeException {
    public AlertsLimitExhaustedException(String s) {
        super(s);
    }
}
