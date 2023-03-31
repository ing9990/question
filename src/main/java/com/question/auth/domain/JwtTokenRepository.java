package com.question.auth.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JwtTokenRepository extends JpaRepository<OAuthToken, Long> {

    @Query("select o from OAuthToken o where o.user.userId = ?1")
    OAuthToken getOAuthTokenByUser_UserId(String userId);

}
