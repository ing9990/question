package com.question.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query("select u from User u where u.email = ?1")
    Optional<User> findByEmail(String email);

    @Query("select u from User u where u.email = ?1")
    User getByEmail(String email);

    @Query("select u from User u where u.username = ?1")
    Optional<User> findUserByUsername(String username);

    @Query("select u from User u where u.email = ?1")
    Optional<User> findUserByEmail(String email);
}
