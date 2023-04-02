package com.question.auth.application;

import com.question.auth.io.response.AuthToken;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthTokenCreater implements TokenCreator {

    private final TokenProvider tokenProvider;

    public Auth createAuthToken(final String userId) {
        return new AuthToken(tokenProvider.createAccessToken(userId), tokenProvider.createRefreshToken(userId));
    }

    @Override
    public AuthToken renewAuthToken(String outRefreshToken) {
        return null;
    }

    @Override
    public Long extractPayload(String accessToken) {
        return null;
    }
}
