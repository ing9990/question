package com.question.question.domain;

public class QuestionNotFoundException extends RuntimeException {
    public QuestionNotFoundException() {
        this("질문글을 찾을 수 없습니다.");
    }

    public QuestionNotFoundException(String message) {
        super(message);
    }
}
