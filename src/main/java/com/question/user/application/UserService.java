package com.question.user.application;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.question.user.event.UserSavedEvent;
import com.question.user.ports.CreateUserPort;
import com.question.user.ports.GetUserPort;
import com.question.user.ports.UpdateUserPort;
import com.question.user.presentation.io.response.UserResponse;

@Service
@Transactional(readOnly = true)
public class UserService {

	private final ApplicationEventPublisher publisher;
	private final CreateUserPort createUserPort;
	private final GetUserPort getUserPort;
	private final UpdateUserPort updateUserPort;

	public UserService(
		final ApplicationEventPublisher publisher,
		final CreateUserPort createUserPort,
		final GetUserPort getUserPort,
		final UpdateUserPort updateUserPort) {

		this.publisher = publisher;
		this.createUserPort = createUserPort;
		this.getUserPort = getUserPort;
		this.updateUserPort = updateUserPort;
	}

	@Transactional
	public UserResponse findById(final String id) {
		return getUserPort.getUserPort(id);
	}

	@Transactional
	public void updateUsername(final String id, final String username) {
		updateUserPort.updateUser(id, username);
	}

	@Transactional
	public void save(String username, String email, String password, String profileImageUrl) {
		createUserPort.createUser(username, email, password, profileImageUrl);

		publisher.publishEvent(new UserSavedEvent(this, username));
	}
}
