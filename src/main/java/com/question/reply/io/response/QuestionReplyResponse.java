package com.question.reply.io.response;

import com.question.user.io.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionReplyResponse {
    private UserResponse user;
    private String title;
    private String content;
}
