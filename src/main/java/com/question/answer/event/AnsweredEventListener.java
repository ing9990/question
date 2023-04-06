package com.question.answer.event;

import com.question.infra.out.io.request.SendMailRequest;
import com.question.infra.out.io.request.SendPushRequest;
import com.question.infra.out.service.email.MailService;
import com.question.infra.out.service.push.PushService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class AnsweredEventListener implements ApplicationListener<AnsweredEvent> {

    private final MailService mailService;
    private final PushService pushService;

    @Override
    @Async
    public void onApplicationEvent(AnsweredEvent event) {
        String message = String.format("%s님이 [%s] 질문글에 답변을 남겼어요,",
                event.getAnswererUsername(), event.getQuestionTitle());

        mailService.sendMail(
                SendMailRequest.builder()
                        .to(event.getAnswererEmail())
                        .from(event.getAuthorEmail())
                        .title(message)
                        .content(message)
                        .build());

        pushService.sendPush(SendPushRequest.builder()
                .pushToken("")
                .pushToken(message)
                .build());
    }

}
