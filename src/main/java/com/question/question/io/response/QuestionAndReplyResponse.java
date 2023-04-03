package com.question.question.io.response;

import com.question.answer.io.response.QuestionReplyResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionAndReplyResponse {

    private QuestionResponse question;

    private List<QuestionReplyResponse> replies;
}
