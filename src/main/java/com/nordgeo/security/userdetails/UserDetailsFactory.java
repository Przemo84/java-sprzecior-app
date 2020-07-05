package com.nordgeo.security.userdetails;

import com.nordgeo.entity.Role;
import com.nordgeo.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDetailsFactory {

    UserDetails createFromUser(User user) {
        if (user.getPassword() == null) {
            return null;
        }

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

        if (user.getRole() != null) {
            Role role = user.getRole();

            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));

        }

        boolean disabled = isDisabled(user);

        String username = user.getUsername();

        if (username == null) {
            username = user.getFirstName() + user.getLastName();
        }

        return org.springframework.security.core.userdetails.User.withUsername(username)
                .password(user.getPassword())
                .disabled(disabled)
                .authorities(grantedAuthorities)
                .build();
    }

    private boolean isDisabled(User user) {
        return !user.getActive();
    }
}
