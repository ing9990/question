package com.question.question.domain;

public class QuestionNotFoundException extends RuntimeException {
    public QuestionNotFoundException() {
        super();
    }

    public QuestionNotFoundException(String message) {
        super(message);
    }
}
