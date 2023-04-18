package com.question.user.adapters;

import org.springframework.stereotype.Service;

import com.question.user.domain.DuplicateUsernameException;
import com.question.user.domain.User;
import com.question.user.domain.UserNotFoundException;
import com.question.user.domain.UserRepository;
import com.question.user.ports.UpdateUserPort;

@Service
public class UpdateUserAdapter implements UpdateUserPort {

	private final UserRepository userRepository;

	public UpdateUserAdapter(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void updateUser(String id, String username) {
		User user = userRepository.findById(id)
			.orElseThrow(UserNotFoundException::new);
		try {
			user.updateUsername(username);
		} catch (Exception e) {
			throw new DuplicateUsernameException("중복된 닉네임입니다.");
		}
	}
}
