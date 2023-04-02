package com.question.auth.application;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider implements TokenProvider {

    private final long validityMS;
    private final long refreshValidityMS;
    private final String secretKey;
    private final String refreshSecretKey;

    public JwtTokenProvider(
            @Value("${security.jwt.token.expire-length}") long validityMS,
            @Value("${security.jwt.refresh-token.expire-length}") long refreshValidityMS,
            @Value("${security.jwt.token.secret-key}") String secretKey,
            @Value("${security.jwt.refresh-token.secret-key}") String refreshSecretKey
    ) {
        this.validityMS = validityMS;
        this.refreshValidityMS = refreshValidityMS;
        this.secretKey = secretKey;
        this.refreshSecretKey = refreshSecretKey;
    }
    
    @Override
    public String extractPayloadFromAccessToken(String accessToken) {
        return null;
    }

    @Override
    public String createAccessToken(String userId) {
        return null;
    }

    @Override
    public String createRefreshToken(String userId) {
        return null;
    }
}
