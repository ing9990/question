package com.question.question.presentation.io.response;

import java.util.List;
import java.util.stream.Collectors;

import com.question.answer.presentation.io.response.QuestionAnswerResponse;
import com.question.question.domain.Question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class QuestionAndAnswersResponse {
	private QuestionResponse question;

	private List<QuestionAnswerResponse> replies;

	public static QuestionAndAnswersResponse of(Question question) {
		List<QuestionAnswerResponse> replies = question.getAnswers()
			.stream().map(QuestionAnswerResponse::of)
			.collect(Collectors.toList());

		return QuestionAndAnswersResponse.builder()
			.question(QuestionResponse.of(question))
			.replies(replies)
			.build();
	}
}
