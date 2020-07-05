package com.nordgeo.security.provider;

import com.nordgeo.persistence.repository.UserRepository;
import com.nordgeo.security.userdetails.UserDetailsService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AdminAuthenticationProvider extends AbstractAuthenticationProvider {

    private UserDetailsService userdetailsService;

    public AdminAuthenticationProvider(UserDetailsService userdetailsService,
                                       PasswordEncoder passwordEncoder ,
                                       UserRepository userRepository) {
        super(passwordEncoder, userRepository);
        this.userdetailsService = userdetailsService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws org.springframework.security.core.AuthenticationException {
        if (!supports(authentication.getClass()))
            return null;

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails user = userdetailsService.loadUserByUsername(name);

        authenticate(user, password);

        return new UsernamePasswordAuthenticationToken(
                name, password, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class);
    }
}