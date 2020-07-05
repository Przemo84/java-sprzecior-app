package com.nordgeo.security.userdetails;

import com.nordgeo.entity.User;
import com.nordgeo.service.user.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    private UserService userService;

    private UserDetailsFactory detailsFactory;

    public UserDetailsServiceImpl(UserService userService, UserDetailsFactory detailsFactory) {
        this.userService = userService;
        this.detailsFactory = detailsFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);

        return createUserDetails(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserById(Integer id) throws UsernameNotFoundException {
        User user = userService.findByEmail(id.toString());

        return createUserDetails(user);
    }

    @Override
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        User user = userService.findByEmail(email);

        return createUserDetails(user);
    }

    private UserDetails createUserDetails(User user) {
        if (user == null)
            return null;

        return detailsFactory.createFromUser(user);
    }
}