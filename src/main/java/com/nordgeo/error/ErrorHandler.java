package com.nordgeo.error;

import com.nordgeo.exception.auth.CodeException;
import com.nordgeo.exception.auth.DemoExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class ErrorHandler {

    public static void handle(Exception e, HttpServletResponse response) throws IOException {
        if (e instanceof CodeException) {
            CodeException exception = (CodeException) e;
            FieldError fieldError = new FieldError(e.getMessage(), exception.getCode());

            ValidationError error = new ValidationError(e.getMessage(), fieldError);

            mapToJson(response, error, HttpServletResponse.SC_UNAUTHORIZED);
        }

        if (e instanceof AuthenticationException) {
            ValidationError error = new ValidationError(e.getMessage());

            if (e instanceof DemoExpiredException) {
                mapToJson(response, error, HttpServletResponse.SC_GONE);
            } else {
                mapToJson(response, error, HttpServletResponse.SC_UNAUTHORIZED);
            }

            return;
        }

        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
    }

    private static void mapToJson(HttpServletResponse response, ValidationError error, int code) throws IOException {
        response.setContentType(APPLICATION_JSON_VALUE);
        response.setStatus(code);

        ObjectMapper objectMapper = new ObjectMapper();
        response.getWriter().write(objectMapper.writeValueAsString(error));
    }
}
