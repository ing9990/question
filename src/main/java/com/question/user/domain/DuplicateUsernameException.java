package com.question.user.domain;

public class DuplicateUsernameException extends RuntimeException {

    public DuplicateUsernameException() {
        this("중복된 닉네임입니다.");
    }

    public DuplicateUsernameException(String message) {
        super(message);
    }

}
