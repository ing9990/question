package com.question.user.domain;

import com.question.commons.BaseTimeEntity;
import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.UUID;
import java.util.regex.Pattern;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user")
public class User extends BaseTimeEntity {

    public static final Pattern USERNAME_PATTERN = Pattern.compile("^[A-Za-z]{5,}$");
    public static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$");

    @Id
    private String userId;

    @Column(name = "email", nullable = false, updatable = false)
    private String email;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "profile_image_url", length = 1000)
    private String profileImageUrl;

    @Enumerated(EnumType.STRING)
    private UserStatus userStatus;

    public void active() {
        this.userStatus = UserStatus.ACTIVE;
    }

    public void inactive() {
        this.userStatus = UserStatus.INACTIVE;
    }

    public void updateUsername(String username) {
        if (!StringUtils.hasText(username)) {
            throw new IllegalArgumentException("username cannot be null");
        }
        this.username = username;
    }

    public void updatePassword(String password) {
        if (!StringUtils.hasText(username)) {
            throw new IllegalArgumentException("username cannot be null");
        }
        this.password = password;
    }

    public User(String username, String email, String password, String profileImageUrl) {
        validateEmail(email);
        validateUsername(username);

        this.userId = UUID.randomUUID().toString();
        this.email = email;
        this.username = username;
        this.profileImageUrl = profileImageUrl;
        this.password = password;
    }

    private void validateUsername(String username) {

    }

    private void validateEmail(String email) {

    }

    private void validateProfileImageUrl(String profileImageUrl) {

    }
}