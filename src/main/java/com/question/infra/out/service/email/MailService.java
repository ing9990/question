package com.question.infra.out.service.email;

public interface MailService {
	void sendMail(String fromEmail, String message);
}
