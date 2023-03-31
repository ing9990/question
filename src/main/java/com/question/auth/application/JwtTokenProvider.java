package com.question.auth.application;

import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider implements TokenProvider {

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
