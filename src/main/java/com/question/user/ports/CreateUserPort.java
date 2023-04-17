package com.question.user.ports;

public interface CreateUserPort {
	void createUser(String username, String email, String password, String profileImageUrl);
}
