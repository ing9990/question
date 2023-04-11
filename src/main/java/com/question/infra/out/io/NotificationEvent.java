package com.question.infra.out.io;

import org.springframework.context.ApplicationEvent;

import lombok.Getter;

@Getter
public class NotificationEvent extends ApplicationEvent {

	private final NotificationType notificationType;
	private final String pushToken;
	private final String fromEmail;
	private final String message;

	public NotificationEvent(
		Object source,
		NotificationType notificationType,
		String message,
		String pushToken,
		String fromEmail) {

		super(source);
		this.notificationType = notificationType;
		this.message = message;
		this.pushToken = pushToken;
		this.fromEmail = fromEmail;
	}
}
