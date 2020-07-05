package com.nordgeo.security.encoder;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("jwt")
public class TokenProperties {

    /**
     * Jwt secret key
     */
    private String secret = "erwnjw1e18d3ghdp193gd7d9113gd773d713gd173dg17d173dg173dg1037dg173dg71dg713d";

    /**
     * Token expiration time in millis
     */
    private long expiration = 2432000000L;

    public String getSecret() {
        return secret;
    }

    public long getExpiration() {
        return expiration;
    }
}
