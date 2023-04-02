package com.question.auth.application;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtTokenProvider implements TokenProvider {

    private final long validityMS;
    private final long refreshValidityMS;

    private final SecretKey key;
    private final SecretKey refreshKey;

    public JwtTokenProvider(
            @Value("${security.jwt.token.expire-length}") long validityMS,
            @Value("${security.jwt.refresh-token.expire-length}") long refreshValidityMS,
            @Value("${security.jwt.token.secret-key}") String secretKey,
            @Value("${security.jwt.refresh-token.secret-key}") String refreshSecretKey
    ) {
        this.validityMS = validityMS;
        this.refreshValidityMS = refreshValidityMS;

        this.key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));
        this.refreshKey = Keys.hmacShaKeyFor(refreshSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public String extractPayloadFromAccessToken(String payload) {
        return null;
    }

    @Override
    public String createAccessToken(String payload) {
        return createToken(payload, validityMS, key);
    }

    @Override
    public String createRefreshToken(String payload) {
        return createToken(payload, refreshValidityMS, refreshKey);
    }


    private String createToken(String payload, long validityMS, SecretKey key) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + validityMS);

        return Jwts.builder()
                .setSubject(payload)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}
