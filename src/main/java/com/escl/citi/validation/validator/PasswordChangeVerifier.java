package com.escl.citi.validation.validator;

import com.escl.citi.security.AuthManager;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class PasswordChangeVerifier implements HandlerInterceptor {

    private AuthManager authManager;

    public PasswordChangeVerifier(AuthManager authManager) {
        this.authManager = authManager;
    }


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            try {
                if (authManager.user().getMustChangePassword() &&
                        !request.getRequestURI().equals("/admin/account/password") &&
                        !request.getRequestURI().equals("/admin/account/save") &&
                        request.getSession() != null) {
                    response.sendRedirect("/admin/account/password?mustChangePassword=1");
                    return false;
                }
            } catch (NullPointerException xe) {
//              do nothing
            }
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {

    }
}
