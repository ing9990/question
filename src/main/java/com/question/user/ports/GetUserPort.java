package com.question.user.ports;

import com.question.user.presentation.io.response.UserResponse;

public interface GetUserPort {
	UserResponse getUserPort(final String userId);
}
