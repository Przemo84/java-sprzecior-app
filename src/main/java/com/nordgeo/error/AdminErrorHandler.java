package com.nordgeo.error;

import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

//@Controller
public class AdminErrorHandler implements ErrorController
{

    private final static String ERROR_PATH = "/error";

    @ExceptionHandler(RuntimeException.class)
    @RequestMapping(value = ERROR_PATH)
    public ModelAndView errorHandler(HttpServletRequest request) {

        String errorPageName = "";
        int httpErrorCode = getErrorCode(request);

        switch (httpErrorCode) {
            case 400: {
                errorPageName = "bad.request.400";
                break;
            }
            case 401: {
                errorPageName = "unauthorized.401";
                break;
            }
            case 403: {
                errorPageName = "forbidden.403";
                break;
            }
            case 404: {
                errorPageName = "not.found.404";
                break;
            }
            case 405: {
                errorPageName = "not.found.404";
                break;
            }
            case 500: {
                errorPageName = "server.error.500";
                break;
            }
        }

        return new ModelAndView(errorPageName);
    }

    private int getErrorCode(HttpServletRequest httpRequest) {
        return (Integer) httpRequest
                .getAttribute("javax.servlet.error.status_code");
    }

    @Override
    public String getErrorPath() {
        return  ERROR_PATH;
    }
}
