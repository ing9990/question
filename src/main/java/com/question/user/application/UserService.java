package com.question.user.application;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.question.user.domain.DuplicateEmailException;
import com.question.user.domain.DuplicateUsernameException;
import com.question.user.domain.User;
import com.question.user.domain.UserNotFoundException;
import com.question.user.domain.UserRepository;
import com.question.user.event.UserSavedEvent;
import com.question.user.io.request.UserUpdateRequest;
import com.question.user.io.response.UserResponse;

@Service
@Transactional(readOnly = true)
public class UserService {

	private final ApplicationEventPublisher publisher;
	private final PasswordEncoder encoder;
	private final UserRepository userRepository;

	public UserService(
		final ApplicationEventPublisher publisher,
		final PasswordEncoder encoder,
		final UserRepository userRepository
	) {
		this.publisher = publisher;
		this.encoder = encoder;
		this.userRepository = userRepository;
	}

	public UserResponse findById(final String id) {
		return UserResponse.of(userRepository.findById(id)
			.orElseThrow(UserNotFoundException::new));
	}

	@Transactional
	public void updateUsername(final String id, final UserUpdateRequest request) {
		hasSameUsername(request.getUsername());

		User user = userRepository.findById(id)
			.orElseThrow(UserNotFoundException::new);

		user.updateUsername(request.getUsername());
	}

	@Transactional
	public void save(String username, String email, String password, String profileImageUrl) {
		hasSameEamil(email);
		hasSameUsername(username);

		User savedUser = userRepository.save(new User(username, email, encoder.encode(password), profileImageUrl));

		publisher.publishEvent(new UserSavedEvent(this, savedUser.getUserId()));
	}

	private void hasSameUsername(String username) {
		if (userRepository.findUserByUsername(username).isPresent()) {
			throw new DuplicateUsernameException();
		}
	}

	public void hasSameEamil(String email) {
		if (userRepository.findUserByEmail(email).isPresent()) {
			throw new DuplicateEmailException();
		}
	}
}
