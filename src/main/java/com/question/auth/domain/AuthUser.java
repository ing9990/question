package com.question.auth.domain;

import com.question.user.domain.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthUser {

    private String email;
    private String username;
    private String profileImageUrl;


    public User toUser() {
        return new User(email, username, profileImageUrl);
    }
}
