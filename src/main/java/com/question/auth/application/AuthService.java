package com.question.auth.application;

import com.question.auth.domain.AuthUser;
import com.question.auth.domain.InvalidAuthenticationException;
import com.question.auth.domain.JwtTokenRepository;
import com.question.user.event.UserSavedEvent;
import com.question.auth.io.response.AccessTokenAndRefreshTokenResponse;
import com.question.user.domain.User;
import com.question.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtTokenRepository jwtTokenRepository;
    private final ApplicationEventPublisher publisher;
    private final PasswordEncoder encoder;
    private final TokenProvider provider;

    @Transactional
    public AccessTokenAndRefreshTokenResponse generateAccessTokenAndRefreshToken(final AuthUser authUser) {
        var foundUser = findUser(authUser);

        return new AccessTokenAndRefreshTokenResponse(
                provider.createAccessToken(foundUser.getUserId()),
                provider.createRefreshToken(foundUser.getUserId()));
    }

    @Transactional(readOnly = true)
    public String getUserIdFromToken(final String token) {
        return provider.getPayload(token);
    }

    private User findUser(AuthUser authUser) {
        String email = authUser.getEmail();

        if (userRepository.findByEmail(email).isPresent()) {
            return userRepository.getByEmail(email);
        }

        return saveUser(authUser.toUser());
    }

    private User saveUser(final User user) {
        var savedUser = userRepository.save(user);

        publisher.publishEvent(new UserSavedEvent(this, savedUser.getUserId()));

        return userRepository.save(user);
    }

    public void checkEmailAndPassword(String email, String password) {

        var foundUser = userRepository.findUserByEmail(email)
                .orElseThrow(InvalidAuthenticationException::new);

        if (! encoder.matches(password, foundUser.getPassword())) {
            throw new InvalidAuthenticationException("패스워드가 다릅니다.");
        }
    }
}
