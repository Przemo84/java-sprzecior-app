package com.nordgeo.utils;

import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

@Component
public class Flash {
    private static MessageSource messageSource;

    public static void success(RedirectAttributes redirectAttributes) {
        init();

        success(redirectAttributes, "action.success", null);
    }

    public static void success(RedirectAttributes redirectAttributes, String msg) {
        init();

        success(redirectAttributes, msg, null);
    }

    public static void error(RedirectAttributes redirectAttributes, String msg) {
        init();

        error(redirectAttributes, msg, null);
    }

    public static void success(RedirectAttributes redirectAttributes, String msg, Object[] params) {
        init();

        redirectAttributes.addFlashAttribute("css", "success");
        try {
            redirectAttributes.addFlashAttribute("msg", messageSource.getMessage(msg, params, Locale.getDefault()));
        } catch (NoSuchMessageException e) {
            redirectAttributes.addFlashAttribute("msg", msg);
        }
    }

    public static void error(RedirectAttributes redirectAttributes, String msg, Object[] params) {
        init();

        redirectAttributes.addFlashAttribute("css", "danger");

        try {
            redirectAttributes.addFlashAttribute("msg", messageSource.getMessage(msg, params, Locale.getDefault()));
        } catch (NoSuchMessageException e) {
            redirectAttributes.addFlashAttribute("msg", msg);
        }
    }

    private static void init() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest();

        WebApplicationContext webAppContext = RequestContextUtils.findWebApplicationContext(request);
        messageSource = (MessageSource) webAppContext.getBean("messageSource");
    }

    public static String translate(String message) {
        init();
        try {
            return messageSource.getMessage(message, null, null);
        } catch (NoSuchMessageException e) {
            return message;
        }
    }
}
