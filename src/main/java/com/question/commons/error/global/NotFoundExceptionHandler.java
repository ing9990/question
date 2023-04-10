package com.question.commons.error.global;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.question.commons.error.dto.Error;
import com.question.commons.error.dto.ErrorResponse;
import com.question.question.domain.QuestionNotFoundException;
import com.question.user.domain.UserNotFoundException;
import com.question.watchlist.domain.WatchlistNotFoundException;

@ControllerAdvice
public class NotFoundExceptionHandler {

	@ExceptionHandler({
		WatchlistNotFoundException.class,
		QuestionNotFoundException.class,
		UserNotFoundException.class
	})
	public ResponseEntity<ErrorResponse> handleNotFound(
		final Exception e
	) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
			.body(ErrorResponse.of(new Error(e.getMessage())));
	}
}
