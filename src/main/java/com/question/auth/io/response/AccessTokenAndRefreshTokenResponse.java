package com.question.auth.io.response;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
public class AccessTokenAndRefreshTokenResponse {
    private String accessToken;

    private String refreshToken;
}
