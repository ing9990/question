package com.question.auth.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface JwtTokenRepository extends JpaRepository<AuthToken, Long> {

    @Query("select o from AuthToken o where o.user.userId = ?1")
    AuthToken getOAuthTokenByUser_UserId(String userId);

}
