package com.question.answer.presentation.io.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostAnswerRequest {

	private String answerTitle;
	private String answerContent;

}
