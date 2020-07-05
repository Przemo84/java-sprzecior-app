package com.nordgeo.error;

import com.nordgeo.exception.AlertsDuplicationAttemptException;
import com.nordgeo.exception.AlertsLimitExhaustedException;
import com.nordgeo.exception.auth.BadCredentialsException;
import com.nordgeo.exception.auth.CredentialsExpiredException;
import com.nordgeo.exception.auth.DemoExpiredException;
import com.nordgeo.exception.auth.DisabledException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice(annotations = RestController.class)
public class RestErrorHandler {


    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.GONE)
    public ValidationError handleException(DemoExpiredException exception) {
        FieldError fieldError = new FieldError(exception.getMessage(), exception.getCode());

        return new ValidationError(exception.getMessage(), fieldError);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationError handleException(MethodArgumentNotValidException exception) {
        return createValidationError(exception.getBindingResult());
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationError handleException(AlertsLimitExhaustedException exception) {
        FieldError fieldError = new FieldError(exception.getMessage(), RestError.Code.ALERTS_LIMIT.code);

        return new ValidationError(exception.getMessage(), fieldError);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ValidationError handleException(AlertsDuplicationAttemptException exception) {
        FieldError fieldError = new FieldError(exception.getMessage(), RestError.Code.ALERT_DUPLICATION.code);

        return new ValidationError(exception.getMessage(), fieldError);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ValidationError handleException(BadCredentialsException exception) {
        FieldError fieldError = new FieldError(exception.getMessage(), exception.getCode());

        return new ValidationError(exception.getMessage(), fieldError);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ValidationError handleException(DisabledException exception) {
        FieldError fieldError = new FieldError(exception.getMessage(), exception.getCode());

        return new ValidationError(exception.getMessage(), fieldError);
    }

    @ExceptionHandler
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ValidationError handleException(CredentialsExpiredException exception) {
        FieldError fieldError = new FieldError(exception.getMessage(), exception.getCode());

        return new ValidationError(exception.getMessage(), fieldError);
    }


    private ValidationError createValidationError(BindingResult bindingResult) {
        return ValidationErrorBuilder.fromBindingErrors(bindingResult);
    }
}

