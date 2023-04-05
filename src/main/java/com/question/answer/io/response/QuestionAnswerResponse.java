package com.question.answer.io.response;

import com.question.answer.domain.Answer;
import com.question.user.io.response.UserResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionAnswerResponse {
    private String answererId;
    private String answererUsername;
    private String title;
    private String content;

    public static QuestionAnswerResponse of(
            final Answer answer
    ) {
        return QuestionAnswerResponse.builder()
                .answererId(answer.getAnswerer().getUserId())
                .answererUsername(answer.getAnswerer().getUsername())
                .title(answer.getTitle())
                .content(answer.getContent())
                .build();
    }
}
