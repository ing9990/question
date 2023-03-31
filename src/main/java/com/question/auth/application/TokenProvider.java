package com.question.auth.application;

public interface TokenProvider {
    String extractPayloadFromAccessToken(String accessToken);

    String createAccessToken(String userId);

    String createRefreshToken(String userId);
}
