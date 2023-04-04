package com.question.user.application;

public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException() {
        this("중복된 이메일입니다.");
    }

    public DuplicateEmailException(String message) {
        super(message);
    }
}
