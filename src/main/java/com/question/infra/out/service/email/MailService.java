package com.question.infra.out.service.email;

import com.question.infra.out.io.request.SendMailRequest;

public interface MailService {
    void sendMail(SendMailRequest sendMailRequest);
}
