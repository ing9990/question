package com.question.infra.out.io;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.question.infra.out.service.email.MailService;
import com.question.infra.out.service.push.PushService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Component
public class NotificationEventLisenter implements ApplicationListener<NotificationEvent> {

	private final MailService mailService;
	private final PushService pushService;

	@Override
	public void onApplicationEvent(NotificationEvent event) {
		log.info("Notification: " + event);
	}

	@EventListener
	public void onNotificationEvent(NotificationEvent notificationEvent) {
		try {
			if (notificationEvent.getNotificationType() == NotificationType.PUSH) {
				pushService.sendPush(notificationEvent.getPushToken(), notificationEvent.getMessage());
			} else if (notificationEvent.getNotificationType() == NotificationType.EMAIL) {
				mailService.sendMail(notificationEvent.getFromEmail(), notificationEvent.getMessage());
			} else {
				pushService.sendPush(notificationEvent.getPushToken(), notificationEvent.getMessage());
				mailService.sendMail(notificationEvent.getFromEmail(), notificationEvent.getMessage());
			}
		} catch (Exception e) {
			log.error("NotificationEventListener onNoticiationEvent Exception: " + e.getMessage());

			e.printStackTrace();
		}
	}
}
