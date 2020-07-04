package com.escl.citi.security.encoder;

import com.escl.citi.entity.User;
import com.escl.citi.exception.auth.TokenExpiredException;

public interface JwtTokenEncoder {

    String encode(User pin);

    String encode(User pin, long ttl);

    String encode(String oldToken);

    User decode(String token) throws TokenExpiredException;
}
