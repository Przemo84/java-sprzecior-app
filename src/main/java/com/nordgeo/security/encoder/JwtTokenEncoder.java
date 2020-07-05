package com.nordgeo.security.encoder;

import com.nordgeo.entity.User;
import com.nordgeo.exception.auth.TokenExpiredException;

public interface JwtTokenEncoder {

    String encode(User pin);

    String encode(User pin, long ttl);

    String encode(String oldToken);

    User decode(String token) throws TokenExpiredException;
}
