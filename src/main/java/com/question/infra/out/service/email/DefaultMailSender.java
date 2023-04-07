package com.question.infra.out.service.email;

import com.question.infra.out.io.request.SendMailRequest;
import org.springframework.stereotype.Service;

@Service
public class DefaultMailSender implements MailService {

    @Override
    public void sendMail(SendMailRequest sendMailRequest) {

    }

}
