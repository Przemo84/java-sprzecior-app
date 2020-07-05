package com.nordgeo.security.encoder;

import com.nordgeo.entity.User;
import com.nordgeo.exception.auth.TokenExpiredException;
import com.nordgeo.persistence.repository.UserRepository;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

@Service
public class JwtTokenEncoderImpl implements JwtTokenEncoder {

    private static final String TOKEN_PREFIX = "Bearer ";

    private TokenProperties properties;
    private UserRepository repository;

    public JwtTokenEncoderImpl(TokenProperties properties, UserRepository repository) {
        this.properties = properties;
        this.repository = repository;
    }

    @Override
    public String encode(User user) {
        return encode(user, properties.getExpiration());
    }

    @Override
    public String encode(User user, long ttl) {
        try {
            JWSSigner signer = new MACSigner(properties.getSecret());

            Date ttlDate = new Date(System.currentTimeMillis() + ttl);

            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(String.valueOf(user.getId()))
                    .expirationTime(ttlDate)
                    .build();

            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS512), claimsSet);

            signedJWT.sign(signer);

            return signedJWT.serialize();
        } catch (JOSEException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String encode(String oldToken) {
        User user = decode(oldToken);

        return encode(user);
    }

    @Override
    public User decode(String token) throws TokenExpiredException {
        token = token.replace(TOKEN_PREFIX, "");

        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            JWSVerifier verifier = new MACVerifier(properties.getSecret());

            if (!signedJWT.verify(verifier)) {
                throw new TokenExpiredException("Jwt token expired");
            }

            if (!new Date().before(signedJWT.getJWTClaimsSet().getExpirationTime())) {
                throw new TokenExpiredException("Jwt token expired");
            }

            String userId = signedJWT.getJWTClaimsSet().getSubject();

            return repository.findOne(Integer.valueOf(userId));
        } catch (ParseException | JOSEException e) {
            throw new TokenExpiredException("Jwt token expired");
        }
    }
}
