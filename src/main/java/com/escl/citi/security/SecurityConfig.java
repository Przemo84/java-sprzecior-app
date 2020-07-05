package com.escl.citi.security;

import com.escl.citi.persistence.repository.UserRepository;
import com.escl.citi.security.provider.AdminAuthenticationProvider;
import com.escl.citi.security.userdetails.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

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
        private PasswordEncoder passwordEncoder;

        @Autowired
        private UserDetailsService userDetailsService;

        @Autowired
        private UserRepository userRepository;

        @Override
        protected void configure(AuthenticationManagerBuilder auth) {
            auth.authenticationProvider(new AdminAuthenticationProvider(userDetailsService, passwordEncoder, userRepository));
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().antMatchers("/resources/**").permitAll();


            http.authorizeRequests()
                    .antMatchers("/admin/**")
                    .hasAuthority("cms")
                    .and()
                    .formLogin()
                    .loginPage(LOGIN_PAGE)
                    .loginProcessingUrl("/signin")
                    .usernameParameter("userid")
                    .passwordParameter("passwd")
                    .defaultSuccessUrl("/admin/dashboard")
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