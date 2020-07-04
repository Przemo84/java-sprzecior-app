package com.escl.citi.security.provider;

import com.escl.citi.entity.User;
import com.escl.citi.error.RestError;
import com.escl.citi.exception.auth.*;
import com.escl.citi.security.authentication.JwtAuthenticationToken;
import com.escl.citi.security.encoder.JwtTokenEncoder;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {


    private JwtTokenEncoder tokenEncoder;

    public JwtAuthenticationProvider(JwtTokenEncoder tokenEncoder) {
        this.tokenEncoder = tokenEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!supports(authentication.getClass()))
            return null;

        String token = (String) authentication.getPrincipal();

        if (token == null || Objects.equals(token, ""))
            return null;

        User user;

        try {
            user = tokenEncoder.decode(token);
        } catch (TokenExpiredException e) {
            throw new CredentialsExpiredException("Token expired", RestError.Code.TOKEN_EXPIRED.getCode());
        }

        if (user == null || user.getId() == null) {
            throw new AuthenticationCredentialsNotFoundException(
                    "Token does not have authentication credentials",
                    RestError.Code.CREDENTIALS.getCode()
            );
        }

        if (!user.getActive()) {
            throw new DisabledException("Account is locked", RestError.Code.DISABLED.getCode());
        }

//        if (user.getType() == Pin.CodeMember.DEMO && user.getDemoExpiration() != null && user.getDemoExpiration().before(new Date())) {
//            throw new DemoExpiredException("Demo expired", RestError.Code.DEMO_EXPIRED.getCode());
//        }

        return new JwtAuthenticationToken(String.valueOf(user.getId()), new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(JwtAuthenticationToken.class);
    }
}