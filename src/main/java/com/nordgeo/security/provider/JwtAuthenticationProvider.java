package com.nordgeo.security.provider;

import com.nordgeo.entity.User;
import com.nordgeo.error.RestError;
import com.nordgeo.security.authentication.JwtAuthenticationToken;
import com.nordgeo.security.encoder.JwtTokenEncoder;
import com.nordgeo.exception.auth.AuthenticationCredentialsNotFoundException;
import com.nordgeo.exception.auth.CredentialsExpiredException;
import com.nordgeo.exception.auth.DisabledException;
import com.nordgeo.exception.auth.TokenExpiredException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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