package com.nordgeo.security.authentication;


import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class PinAuthenticationToken extends AbstractAuthenticationToken {

    private String token;
    private String deviceToken;

    public PinAuthenticationToken(String token, String deviceToken) {
        super(null);
        this.token = token;
        this.deviceToken = deviceToken;
    }

    public PinAuthenticationToken(String token, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    @Override
    public boolean isAuthenticated() {
        return true;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

    @Override
    public Object getPrincipal() {
        return getToken();
    }

    @Override
    public String getName() {
        return getToken();
    }
}
