package com.question.user.io.response;

import com.question.user.domain.User;
import com.question.user.domain.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private String userId;
    private String username;
    private String email;
    private String profileImageUrl;
    private UserStatus status;

    public static UserResponse of(final User user) {
        return UserResponse.builder()
                .userId(user.getUserId())
                .username(user.getUsername())
                .email(user.getEmail())
                .profileImageUrl(user.getProfileImageUrl())
                .status(user.getUserStatus())
                .build();
    }
}