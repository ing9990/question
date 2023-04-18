package com.question.auth.presentation;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.question.auth.application.AuthService;
import com.question.auth.domain.AuthUser;
import com.question.auth.presentation.io.request.LoginRequest;
import com.question.auth.presentation.io.request.RefreshTokenRenewRequest;
import com.question.auth.presentation.io.response.AccessTokenAndRefreshTokenResponse;
import com.question.infra.in.aop.support.CurrentUser;

@RestController
@RequestMapping("/api/auth")
public class AuthApi {

	private final AuthService authService;

	public AuthApi(
		final AuthService authService
	) {
		this.authService = authService;
	}

	@PostMapping("/login")
	public ResponseEntity<AccessTokenAndRefreshTokenResponse> login(
		@RequestBody @Valid LoginRequest loginRequest
	) {
		authService.checkEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());

		return ResponseEntity.status(HttpStatus.OK)
			.body(authService.generateAccessTokenAndRefreshToken(
				new AuthUser(
					loginRequest.getEmail(),
					loginRequest.getPassword(),
					null))
			);
	}

	@PostMapping("/renew")
	public ResponseEntity<AccessTokenAndRefreshTokenResponse> renewTokenExpiration(
		@Valid @RequestBody RefreshTokenRenewRequest request,
		@CurrentUser String userId
	) {
		return ResponseEntity.status(HttpStatus.OK)
			.body(authService.renewTokenExpiration(userId, request.getRefreshToken()));
	}
}
