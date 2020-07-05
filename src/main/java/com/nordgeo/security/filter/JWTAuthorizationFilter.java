package com.nordgeo.security.filter;

import com.nordgeo.error.ErrorHandler;
import com.nordgeo.security.authentication.JwtAuthenticationToken;
import com.nordgeo.security.provider.JwtAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";

    private final JwtAuthenticationProvider tokenAuthenticationProvider;

    public JWTAuthorizationFilter(AuthenticationManager authManager, JwtAuthenticationProvider tokenAuthenticationProvider) {
        super(authManager);
        this.tokenAuthenticationProvider = tokenAuthenticationProvider;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
        String token = req.getHeader(HEADER_STRING);

        if (token == null || !token.startsWith(TOKEN_PREFIX)) {
            chain.doFilter(req, res);
            return;
        }

        final JwtAuthenticationToken authRequest = new
                JwtAuthenticationToken(token);

        try {
            Authentication authentication = tokenAuthenticationProvider.authenticate(authRequest);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (AuthenticationException e) {
            ErrorHandler.handle(e, res);
            return;
        }

        chain.doFilter(req, res);
    }
}