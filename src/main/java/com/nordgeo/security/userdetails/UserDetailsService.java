package com.nordgeo.security.userdetails;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public interface UserDetailsService extends org.springframework.security.core.userdetails.UserDetailsService {

    UserDetails loadUserById(Integer id) throws UsernameNotFoundException;

    UserDetails loadUserByEmail(String email) throws UsernameNotFoundException;
}
