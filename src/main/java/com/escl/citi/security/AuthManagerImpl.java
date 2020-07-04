package com.escl.citi.security;

import com.escl.citi.entity.User;
import com.escl.citi.persistence.repository.UserRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class AuthManagerImpl implements AuthManager {

    private UserRepository userRepository;


    public AuthManagerImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User user() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            if (!(authentication instanceof AnonymousAuthenticationToken)) {
                String name = authentication.getName();

                User user = userRepository.findByUsername(name);

                if (user == null) {
                    user = userRepository.findByEmail(name);
                }

                if (user == null) {
                    user = userRepository.findOne(Integer.valueOf(name));
                }

                return user;
            }
        }

        return null;
    }

}
