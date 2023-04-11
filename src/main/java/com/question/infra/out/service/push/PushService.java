package com.question.infra.out.service.push;

public interface PushService {
	void sendPush(String pushToken, String message);
}
