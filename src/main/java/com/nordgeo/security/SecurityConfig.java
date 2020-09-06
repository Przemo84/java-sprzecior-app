package com.nordgeo.security;

import com.nordgeo.persistence.repository.UserRepository;
import com.nordgeo.security.provider.AdminAuthenticationProvider;
import com.nordgeo.security.userdetails.UserDetailsService;
import com.nordgeo.service.user.UserService;
import com.nordgeo.service.user.activity.UserActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
//@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder());

    }


    @Order(2)
    @Configuration
    public static class WebSecurityConfig extends WebSecurityConfigurerAdapter {

        private static final String LOGIN_PAGE = "/login";

        @Autowired
        private AuthenticationSuccessHandler successHandler;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        private UserDetailsService userDetailsService;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private UserService userService;

        @Autowired
        private UserActivitiesService userActivitiesService;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) {
            auth.authenticationProvider(new AdminAuthenticationProvider(userDetailsService, passwordEncoder, userRepository, userService, userActivitiesService));
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().antMatchers("/resources/**").permitAll();


            http.authorizeRequests()
                    .antMatchers("/admin/**")
                    .hasAuthority("Admin")
                    .and()
                    .formLogin()
                    .loginPage(LOGIN_PAGE)
                    .loginProcessingUrl("/signin")
                    .usernameParameter("userid")
                    .passwordParameter("passwd")
                    .successHandler(successHandler)
                    .failureUrl(LOGIN_PAGE).permitAll()
                    .and()
                    .logout()
                    .logoutUrl("/signout")
                    .logoutSuccessUrl(LOGIN_PAGE)
                    .permitAll();
                   // .and()
                  //  .csrf().disable();

        }
    }
}