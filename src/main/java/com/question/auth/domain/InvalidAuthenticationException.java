package com.question.auth.domain;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class InvalidAuthenticationException extends RuntimeException {

    public InvalidAuthenticationException() {
        this("인증 정보가 없습니다.");
    }

    public InvalidAuthenticationException(String message) {
        super(message);
    }
}
