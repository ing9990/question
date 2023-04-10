package com.question.commons.error.global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.question.commons.error.dto.Error;
import com.question.commons.error.dto.ErrorResponse;

@ControllerAdvice
public class IllegalArgumentHandler {

	@ExceptionHandler({IllegalArgumentException.class})
	public ResponseEntity<ErrorResponse> handlerInvalidAuthentication(
		final Exception exception
	) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
			.body(ErrorResponse.of(new Error(exception.getMessage())));
	}
}
