package com.nordgeo.security;

import com.nordgeo.error.ErrorHandler;
import com.nordgeo.security.encoder.JwtTokenEncoder;
import com.nordgeo.security.filter.JWTAuthorizationFilter;
import com.nordgeo.security.filter.JWTRefreshFilter;
import com.nordgeo.security.provider.ApiAuthenticationProvider;
import com.nordgeo.security.provider.JwtAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Order(1)
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class ApiSecurityConfig extends WebSecurityConfigurerAdapter {

//    @Autowired
//    @Qualifier("mobileUserDetailsServiceImpl")
//    private UserDetailsService userDetailsService;

    @Autowired
    private JwtTokenEncoder jwtTokenEncoder;

    @Autowired
    private JwtAuthenticationProvider tokenAuthenticationProvider;

    @Autowired
    private ApiAuthenticationProvider apiAuthenticationProvider;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    @Autowired
//    private MobileUserService userService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(apiAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .antMatcher("/api/**")
                .addFilter(new JWTAuthorizationFilter(authenticationManager(), tokenAuthenticationProvider))
                .addFilterAfter(new JWTRefreshFilter(jwtTokenEncoder), JWTAuthorizationFilter.class)

                .authorizeRequests()
                .antMatchers("/api/fcm/**", "/api/user/**", "/api/secured/**")
                .authenticated()

                .antMatchers("/api/auth/**").permitAll()
                .and()
                .exceptionHandling()
                .authenticationEntryPoint((request, response, e) -> ErrorHandler.handle(e, response))
                .and()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .csrf().disable();
    }
}

