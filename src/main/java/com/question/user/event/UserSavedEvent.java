package com.question.user.event;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class UserSavedEvent extends ApplicationEvent {

    private final String userId;

    public UserSavedEvent(Object source, String userId) {
        super(source);
        this.userId = userId;
    }

}
