package com.question.user.domain;

import com.question.commons.BaseTimeEntity;
import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.UUID;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user")
public class User extends BaseTimeEntity {

    @Id
    private String userId;

    @Column
    private String username;

    @Column
    private String password;

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

    public User(String username, String password) {
        if (!StringUtils.hasText(username)) {
            throw new IllegalArgumentException("username cannot be null");
        }

        if (!StringUtils.hasText(password)) {
            throw new IllegalArgumentException("paassword cannot be null");
        }

        this.userId = UUID.randomUUID().toString();
        this.username = username;
        this.password = password;
    }
}