package com.question.auth.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class JwtTokenProviderTest {

    private TokenProvider tokenProvider;

    @BeforeEach
    void setUp(
        @Value("${security.jwt.token.expire-length}") long validityMS,
        @Value("${security.jwt.refresh-token.expire-length}") long refreshValidityMS,
        @Value("${security.jwt.token.secret-key}") String secretKey,
        @Value("${security.jwt.refresh-token.secret-key}") String refreshSecretKey) {

        tokenProvider = new JwtTokenProvider(validityMS, refreshValidityMS, secretKey, refreshSecretKey);
    }

    @DisplayName("Access Token 생성 및 검증 테스트")
    void createAccessTokenAndValidate() {
        String payload = "testUser";
        String accessToken = tokenProvider.createAccessToken(payload);

        assertNotNull(accessToken);

        String subject = tokenProvider.getPayload(accessToken);
        assertEquals(payload, subject);

        assertDoesNotThrow(() -> tokenProvider.validateToken(accessToken));
    }
}