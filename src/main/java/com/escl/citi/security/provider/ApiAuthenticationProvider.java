package com.escl.citi.security.provider;

import com.escl.citi.entity.User;
import com.escl.citi.error.RestError;
import com.escl.citi.exception.auth.BadCredentialsException;
import com.escl.citi.persistence.repository.UserRepository;
import com.escl.citi.security.authentication.PinAuthenticationToken;
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