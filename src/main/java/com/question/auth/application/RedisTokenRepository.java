package com.question.auth.application;

import org.springframework.stereotype.Repository;

@Repository
public class RedisTokenRepository implements TokenRepository {

    @Override
    public String save(String userId, String refreshToken) {
        return null;
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public void deleteByMemberId(String userId) {

    }

    @Override
    public String getToken(String userId) {
        return null;
    }

    @Override
    public boolean existByUserId(String userId) {
        return false;
    }
}
