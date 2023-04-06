package com.question.commons.error.global;

import com.question.commons.error.dto.Error;
import com.question.commons.error.dto.ErrorResponse;
import com.question.user.domain.DuplicateEmailException;
import com.question.user.domain.DuplicateUsernameException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class DuplicatedExceptionHandler {

    @ExceptionHandler({DuplicateUsernameException.class, DuplicateEmailException.class})
    public ResponseEntity<ErrorResponse> duplicatedDataHandler(Exception e) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(ErrorResponse.of(new Error(e.getMessage())));
    }
}
