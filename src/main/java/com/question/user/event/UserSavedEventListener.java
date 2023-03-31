package com.question.user.event;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;

@Slf4j
public class UserSavedEventListener implements ApplicationListener<UserSavedEvent> {

    @Override
    public void onApplicationEvent(UserSavedEvent event) {
        log.info(String.format("새로운 유저: [%s]", event.getUserId()));
    }

    @EventListener
    public void sendPush(UserSavedEvent event) {
        // FCM 연동
    }

    @EventListener
    public void sendSMS(UserSavedEvent event) {
        // Twilio 연동
    }

    @EventListener
    public void sendEmail(UserSavedEvent event) {
        // 메일 발송
    }

}
