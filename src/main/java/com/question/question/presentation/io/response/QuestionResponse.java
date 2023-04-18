package com.question.question.presentation.io.response;

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
	private String authorUsername;
	private String title;
	private String detail;

	public static QuestionResponse of(Question question) {
		return QuestionResponse.builder()
			.questionId(question.getQuestionId())
			.title(question.getTitle())
			.authorUsername(question.getAuthor().getUsername())
			.detail(question.getDetail())
			.build();
	}
}
