package com.question.auth.application;

import com.question.auth.io.response.AuthToken;

public interface TokenCreator {
    AuthToken createAuthToken(final String userId);

    AuthToken renewAuthToken(final String outRefreshToken);

    Long extractPayload(final String accessToken);
}