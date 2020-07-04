package com.escl.citi.security.filter;

import com.escl.citi.security.encoder.JwtTokenEncoder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTRefreshFilter extends OncePerRequestFilter {

    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String HEADER_STRING = "Authorization";

    private final JwtTokenEncoder tokenEncoder;

    public JWTRefreshFilter(JwtTokenEncoder tokenEncoder) {
        this.tokenEncoder = tokenEncoder;
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

        res.addHeader(HEADER_STRING, TOKEN_PREFIX + tokenEncoder.encode(token));

        chain.doFilter(req, res);
    }
}