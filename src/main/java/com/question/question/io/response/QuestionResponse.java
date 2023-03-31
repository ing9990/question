package com.question.question.io.response;

import com.question.question.domain.Question;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionResponse {
    private Long questionId;
    private String title;
    private String detail;

    public static QuestionResponse of(Question question) {
        return QuestionResponse.builder()
                .questionId(question.getQuestionId())
                .title(question.getTitle())
                .detail(question.getDetail())
                .build();
    }
}
