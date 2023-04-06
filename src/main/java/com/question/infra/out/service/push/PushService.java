package com.question.infra.out.service.push;

import com.question.infra.out.io.request.SendPushRequest;

public interface PushService {
    void sendPush(SendPushRequest sendPushRequest);
}
