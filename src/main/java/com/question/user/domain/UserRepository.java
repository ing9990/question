package com.question.user.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {

    @Query("select u from User u where u.email = ?1")
    boolean findByEmail(String email);

    @Query("select u from User u where u.email = ?1")
    User getByEmail(String email);
}
