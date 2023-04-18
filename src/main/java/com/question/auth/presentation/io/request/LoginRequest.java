package com.question.auth.presentation.io.request;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginRequest {

	@NotEmpty(message = "Email cannot be null")
	private String email;

	@NotEmpty(message = "Password cannot be null")
	private String password;

}
