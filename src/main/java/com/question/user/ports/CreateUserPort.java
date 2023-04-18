package com.question.user.ports;

public interface CreateUserPort {
	void createUser(final String username, final String email, final String password, final String profileImageUrl);
}
