package com.question.question.presentation.hateos;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.question.commons.BaseResponse;

import lombok.Getter;

@Getter
public class QuestionHateos extends RepresentationModel<QuestionHateos> {

	private final BaseResponse content;

	@JsonCreator
	public QuestionHateos(@JsonProperty("content") BaseResponse content) {
		this.content = content;
	}
}
