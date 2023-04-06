package com.question.commons.error.global;

import com.question.commons.error.dto.Error;
import com.question.commons.error.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;


/**
 * TODO: ControllerAdvice에 AOP 적용해서 로깅하기.
 */
@ControllerAdvice
public class RequestArgumentNotValidExceptionHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ResponseEntity<ErrorResponse> methodArgsNotValidException(
            final MethodArgumentNotValidException exception
    ) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(ErrorResponse.of(exception.getBindingResult().getAllErrors()
                        .stream().map(error -> new Error(error.getDefaultMessage()))
                        .collect(Collectors.toList()), HttpStatus.BAD_REQUEST));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handlerUnexpectedException(
            final Exception exception
    ) {
        Error unExpectedError = new Error(exception.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ErrorResponse.of(unExpectedError));
    }
}