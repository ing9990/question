package com.question.auth.domain;

import com.question.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface JwtTokenRepository extends JpaRepository<AuthToken, Long> {

    @Query("select o from AuthToken o where o.user.userId = ?1")
    AuthToken getOAuthTokenByUser_UserId(String userId);


    @Query("select a from AuthToken a where a.user.userId = ?1")
    Optional<AuthToken> findOAuthTokenByUser_UserId(String userId);

    @Query("select a from AuthToken a where a.user = ?1")
    AuthToken getAuthTokenByUser(User user);
}
