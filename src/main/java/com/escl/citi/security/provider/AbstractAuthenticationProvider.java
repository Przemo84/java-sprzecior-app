package com.escl.citi.security.provider;

import com.escl.citi.entity.User;
import com.escl.citi.exception.auth.DisabledException;
import com.escl.citi.persistence.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

abstract class AbstractAuthenticationProvider implements AuthenticationProvider {

    protected PasswordEncoder passwordEncoder;

    protected UserRepository userRepository;

    public AbstractAuthenticationProvider(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    protected void authenticate(UserDetails user, String password) throws AuthenticationException {
        if (user == null) {
            throw new BadCredentialsException("Wrong email or password");
        }

        if (!user.isEnabled()) {
            throw new DisabledException("account is disabled");
        }

        User thisUser = userRepository.findByUsername(user.getUsername());

        if (!passwordEncoder.matches(password, user.getPassword())) {

            Integer loginAttempts = thisUser.getLoginAttempts();

            if (loginAttempts >= 5) {
                thisUser.setActive(false);
                thisUser.setMustChangePassword(true);
                thisUser.setLockDate(new Date());
            }
            thisUser.setLoginAttempts(loginAttempts + 1);
            userRepository.save(thisUser);

            throw new BadCredentialsException("Wrong email or password");
        }

        thisUser.setLoginAttempts(0);
        userRepository.save(thisUser);
    }


}
