package com.nordgeo.error;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

public class ValidationError {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private Integer code;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<FieldError> errors = new ArrayList<>();

    private final String errorMessage;

    public ValidationError(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ValidationError(String errorMessage, Integer code) {
        this.errorMessage = errorMessage;
        this.code = code;
    }

    public ValidationError(String errorMessage, FieldError error) {
        this.errorMessage = errorMessage;
        this.errors.add(error);
    }

    public void addValidationError(FieldError error) {
        errors.add(error);
    }

    public List<FieldError> getErrors() {
        return errors;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public Integer getCode() {
        return code;
    }
}

