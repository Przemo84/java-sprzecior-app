package com.nordgeo.security.provider;

import com.nordgeo.entity.User;
import com.nordgeo.error.RestError;
import com.nordgeo.exception.auth.BadCredentialsException;
import com.nordgeo.persistence.repository.UserRepository;
import com.nordgeo.security.authentication.PinAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class ApiAuthenticationProvider implements AuthenticationProvider {

    private UserRepository userRepository;

    public ApiAuthenticationProvider(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!supports(authentication.getClass()))
            return null;

        PinAuthenticationToken authenticationToken = (PinAuthenticationToken) authentication;

        String token = (String) authentication.getPrincipal();
        String deviceToken = authenticationToken.getDeviceToken();

//        User user = userRepository.findByCodeAndDeviceToken(token, deviceToken);
        User user = new User();

        if (user == null) {
            throw new BadCredentialsException("Wrong credentials", RestError.Code.CREDENTIALS.getCode());
        }



        return new PinAuthenticationToken(token, new ArrayList<SimpleGrantedAuthority>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(PinAuthenticationToken.class);
    }
}