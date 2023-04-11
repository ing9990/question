package com.question.infra.out.service.push;

import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FcmPushService implements PushService {

	@Override
	public void sendPush(String pushToken, String message) {
	}
}
