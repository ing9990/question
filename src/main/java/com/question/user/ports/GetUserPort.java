package com.question.user.ports;

import com.question.user.io.response.UserResponse;

public interface GetUserPort {
	UserResponse getUserPort(String userId);
}
