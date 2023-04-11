package com.question.infra.out.service.email;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class DefaultMailSender implements MailService {

	private final JavaMailSender sender = new JavaMailSenderImpl();

	@Override
	public void sendMail(String fromEmail, String message) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();

		mailMessage.setFrom(fromEmail);
		mailMessage.setSubject("Question Notification!!\uD83D\uDD25");
		mailMessage.setText(message);

		sender.send(mailMessage);
	}
}
