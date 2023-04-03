package com.question.auth.domain;

import com.question.commons.BaseTimeEntity;
import com.question.user.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Entity
@NoArgsConstructor
@Table(name = "auth_token")
public class AuthToken extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "token_id")
    private Long tokenId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "refresh_token", nullable = false)
    private String refreshToken;

    public void change(final String refreshToken) {
        if (!Objects.isNull(refreshToken)) {
            this.refreshToken = refreshToken;
        }
    }

    public AuthToken(
            final User user,
            final String refreshToken
    ) {
        this.user = user;
        this.refreshToken = refreshToken;
    }
}
