package com.question.commons.error.global;

import com.question.auth.domain.InvalidAuthenticationException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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
