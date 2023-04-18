package com.question.user.adapters;

import org.springframework.stereotype.Service;

import com.question.user.domain.UserNotFoundException;
import com.question.user.domain.UserRepository;
import com.question.user.ports.GetUserPort;
import com.question.user.presentation.io.response.UserResponse;

@Service
public class GetUserAdapter implements GetUserPort {

	private final UserRepository userRepository;

	public GetUserAdapter(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserResponse getUserPort(String userId) {
		return UserResponse.of(userRepository.findById(userId)
			.orElseThrow(UserNotFoundException::new));
	}
}
