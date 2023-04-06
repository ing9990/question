package com.question.answer.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;


@Getter
public class AnsweredEvent extends ApplicationEvent {

    private final String answererUserId;
    private final String answererUsername;
    private final String questionTitle;

    private final String answererEmail;
    private final String authorEmail;


    public AnsweredEvent(Object source,
                         final String answererUserId,
                         final String answererUsername,
                         final String questionTitle,
                         final String answererEmail,
                         final String authorEmail) {
        super(source);

        this.answererUserId = answererUserId;
        this.answererUsername = answererUsername;
        this.questionTitle = questionTitle;
        this.answererEmail = answererEmail;
        this.authorEmail = authorEmail;
    }
}
