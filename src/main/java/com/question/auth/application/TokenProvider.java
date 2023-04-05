package com.question.auth.application;

public interface TokenProvider {
    String createAccessToken(final String payload);

    String createRefreshToken(final String payload);

    String getPayload(final String token);

    void validateToken(final String token);

    long getExpirationTimeForRefresh(String token);
}
