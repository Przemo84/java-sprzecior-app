package com.nordgeo.error;


import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;


class ValidationErrorBuilder {

    static ValidationError fromBindingErrors(Errors errors) {
        ValidationError error = new ValidationError("Validation failed. " + errors.getErrorCount() + " error(s)");

        for (FieldError fieldError : errors.getFieldErrors()) {
            RestError.Code code = ErrorCodeFactory.create(fieldError.getCode());

            error.addValidationError(new com.nordgeo.error.FieldError(
                    fieldError.getField(),
                    fieldError.getDefaultMessage(),
                    code.getCode()
            ));
        }


        return error;
    }
}

