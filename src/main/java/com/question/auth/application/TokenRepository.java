package com.question.auth.application;

public interface TokenRepository {
    String save(final String userId, final String refreshToken);

    void deleteAll();

    void deleteByMemberId(final String userId);

    String getToken(final String userId);

    boolean existByUserId(final String userId);
}
