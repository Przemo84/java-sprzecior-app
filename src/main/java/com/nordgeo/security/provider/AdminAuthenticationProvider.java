package com.nordgeo.security.provider;

import com.nordgeo.entity.User;
import com.nordgeo.persistence.repository.UserRepository;
import com.nordgeo.security.userdetails.UserDetailsService;
import com.nordgeo.service.history.ActionHistoryService;
import com.nordgeo.service.user.UserService;
import com.nordgeo.service.user.activity.UserActivitiesService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AdminAuthenticationProvider extends AbstractAuthenticationProvider {

    private UserDetailsService userdetailsService;

    private UserService userService;

    private UserActivitiesService userActivitiesService;

    public AdminAuthenticationProvider(UserDetailsService userdetailsService,
                                       PasswordEncoder passwordEncoder,
                                       UserRepository userRepository,
                                       UserService userService,
                                       UserActivitiesService actionHistoryService) {
        super(passwordEncoder, userRepository);
        this.userdetailsService = userdetailsService;
        this.userService = userService;
        this.userActivitiesService = actionHistoryService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws org.springframework.security.core.AuthenticationException {
        if (!supports(authentication.getClass()))
            return null;

        String name = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails user = userdetailsService.loadUserByUsername(name);

        authenticate(user, password);

        User realUser = userService.findByUsername(user.getUsername());
        userService.setLastLoginDate(realUser);
        saveUserActionHistory(realUser, "Logowanie");

        return new UsernamePasswordAuthenticationToken(
                name, password, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class);
    }

    private void saveUserActionHistory(User user, String action) {

        if (user.getRole().getName().equals("Admin")) {
            userActivitiesService.saveActivity(user, action + " Administratora: " + user.getFullName() + ", ID: <a href=\"/admin/admins/form/"
                    + user.getId() + "\">" + user.getId() + "</a>");
        } else {
            userActivitiesService.saveActivity(user, action + " Pracownika: " + user.getFullName() + ", ID: <a href=\"/admin/employees/form/"
                    + user.getId() + "\">" + user.getId() + "</a>");
        }

    }

}