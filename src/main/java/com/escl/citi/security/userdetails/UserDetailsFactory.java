package com.escl.citi.security.userdetails;

import com.escl.citi.entity.Role;
import com.escl.citi.entity.User;
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

        grantedAuthorities.add(new SimpleGrantedAuthority("cms"));

        if (user.getRole() != null) {
            Role role = user.getRole();

            grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));


//            if (!role.getName().equals("Dealer"))
//                grantedAuthorities.add(new SimpleGrantedAuthority("can_change_assign_pin"));
//
//            if (!role.getName().equals("Administrator"))
//                grantedAuthorities.add(new SimpleGrantedAuthority("can_generate_pin"));
//
//            if(role.getName().equalsIgnoreCase("Developer")){
//                grantedAuthorities.add(new SimpleGrantedAuthority("can_change_assign_pin"));
//                grantedAuthorities.add(new SimpleGrantedAuthority("can_generate_pin"));
//            }
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
