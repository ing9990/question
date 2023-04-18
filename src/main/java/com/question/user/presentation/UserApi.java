package com.question.user.presentation;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.question.infra.in.aop.support.CurrentUser;
import com.question.user.application.UserService;
import com.question.user.presentation.io.request.SignUpRequest;
import com.question.user.presentation.io.request.UserUpdateRequest;
import com.question.user.presentation.io.response.UserResponse;

@RestController
@RequestMapping("/api/users")
public class UserApi {

	private final UserService userService;

	public UserApi(
		final UserService userService
	) {
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<UserResponse> findMe(@CurrentUser final String userId) {
		return ResponseEntity.status(HttpStatus.OK).body(userService.findById(userId));
	}

	@PostMapping
	public ResponseEntity<Void> singUp(
		@Valid @RequestBody final SignUpRequest signUpRequest
	) {
		userService.save(
			signUpRequest.getUsername(),
			signUpRequest.getEmail(),
			signUpRequest.getPassword(),
			signUpRequest.getProfileImageUrl()
		);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PatchMapping
	public ResponseEntity<Void> updateUsername(
		@Valid @RequestBody final UserUpdateRequest request,
		@CurrentUser final String userId
	) {
		userService.updateUsername(userId, request.getUsername());

		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
