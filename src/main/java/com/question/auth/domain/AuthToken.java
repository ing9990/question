package com.question.auth.domain;

import lombok.*;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AuthToken {
    private String accessToken;

    private String refreshToken;
}
