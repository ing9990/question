package com.question.user.adapters;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.question.user.domain.User;
import com.question.user.domain.UserRepository;
import com.question.user.ports.CreateUserPort;

@Service
public class CreateUserAdapter implements CreateUserPort {

	private final UserRepository userRepository;
	private final PasswordEncoder encoder;

	public CreateUserAdapter(
		final UserRepository userRepository,
		final PasswordEncoder encoder
	) {
		this.userRepository = userRepository;
		this.encoder = encoder;
	}

	@Override
	public void createUser(final String username,
		final String email,
		final String password,
		final String profileImageUrl) {
		userRepository.save(User.userWithAllArgs(username, email, encoder.encode(password), profileImageUrl));
	}
}
