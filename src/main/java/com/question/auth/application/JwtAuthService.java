package com.question.auth.application;

import org.springframework.stereotype.Service;

@Service
public class JwtAuthService implements AuthService {

    @Override
    public String getUserId(String accessToken) {
        return null;
    }
}
