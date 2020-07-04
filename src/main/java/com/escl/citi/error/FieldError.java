package com.escl.citi.error;

import com.fasterxml.jackson.annotation.JsonInclude;

class FieldError {

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private String field;

    private String message;

    private Integer code;

    FieldError(String message, Integer code) {
        this.message = message;
        this.code = code;
    }


    FieldError(String field, String message, Integer code) {
        this(message, code);

        this.field = field;
    }

    public String getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }

    public Integer getCode() {
        return code;
    }
}

