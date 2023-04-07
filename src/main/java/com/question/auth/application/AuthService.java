package com.question.auth.application;

import java.util.Optional;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.question.auth.domain.AuthToken;
import com.question.auth.domain.AuthUser;
import com.question.auth.domain.InvalidAuthenticationException;
import com.question.auth.domain.JwtTokenRepository;
import com.question.auth.io.response.AccessTokenAndRefreshTokenResponse;
import com.question.user.domain.User;
import com.question.user.domain.UserNotFoundException;
import com.question.user.domain.UserRepository;
import com.question.user.event.UserSavedEvent;

@Service
@Transactional
public class AuthService {

	private final UserRepository userRepository;
	private final JwtTokenRepository jwtTokenRepository;
	private final ApplicationEventPublisher publisher;
	private final PasswordEncoder encoder;
	private final TokenProvider provider;

	public AuthService(
		final UserRepository userRepository,
		final JwtTokenRepository jwtTokenRepository,
		final ApplicationEventPublisher publisher,
		final PasswordEncoder encoder,
		final TokenProvider provider
	) {
		this.userRepository = userRepository;
		this.jwtTokenRepository = jwtTokenRepository;
		this.publisher = publisher;
		this.encoder = encoder;
		this.provider = provider;
	}

	@Transactional
	public AccessTokenAndRefreshTokenResponse generateAccessTokenAndRefreshToken(final AuthUser authUser) {
		User foundUser = findUser(authUser);

		String accessToken = provider.createAccessToken(foundUser.getUserId());
		String refreshToken = provider.createRefreshToken(foundUser.getUserId());

		long expiration = provider.getExpirationTimeForRefresh(refreshToken);

		jwtTokenRepository.save(new AuthToken(foundUser, refreshToken, expiration));
		return new AccessTokenAndRefreshTokenResponse(accessToken, refreshToken);
	}

	@Transactional(readOnly = true)
	public String getUserIdFromToken(final String token) {
		return provider.getPayload(token);
	}

	private User getUser(String userId) {
		Optional<User> foundUser = userRepository.findById(userId);

		if (foundUser.isPresent()) {
			return foundUser.get();
		}

		throw new UserNotFoundException();
	}

	private User findUser(AuthUser authUser) {
		String email = authUser.getEmail();

		if (userRepository.findByEmail(email).isPresent()) {
			return userRepository.getByEmail(email);
		}

		return saveUser(authUser.toUser());
	}

	private User saveUser(final User user) {
		User savedUser = userRepository.save(user);

		publisher.publishEvent(new UserSavedEvent(this, savedUser.getUserId()));

		return userRepository.save(user);
	}

	public void checkEmailAndPassword(final String email, final String password) {
		User foundUser = userRepository.findUserByEmail(email).orElseThrow(InvalidAuthenticationException::new);

		if (!encoder.matches(password, foundUser.getPassword())) {
			throw new InvalidAuthenticationException("패스워드가 다릅니다.");
		}
	}

	private long getExpirationTime(final String token) {
		provider.validateToken(token);
		return provider.getExpirationTimeForRefresh(token);
	}

	public AccessTokenAndRefreshTokenResponse renewTokenExpiration(String userId, String oldRefreshToken) {
		AuthToken token = jwtTokenRepository.findOAuthTokenByUser_UserId(userId)
			.orElseThrow(InvalidAuthenticationException::new);

		User user = token.getUser();

		String newAccessToken = provider.createAccessToken(user.getUserId());
		String newRefreshToken = provider.createRefreshToken(user.getUserId());

		token.change(oldRefreshToken, newRefreshToken, provider.getExpirationTimeForRefresh(newRefreshToken));

		return new AccessTokenAndRefreshTokenResponse(newAccessToken, newRefreshToken);
	}

	public void validateToken(String token) {
		provider.validateToken(token);
	}
}
