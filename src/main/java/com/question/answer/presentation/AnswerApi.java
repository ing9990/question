package com.question.answer.presentation;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.question.answer.application.AnswerService;
import com.question.answer.presentation.io.request.PostAnswerRequest;
import com.question.infra.in.aop.support.CurrentUser;

@RestController
@RequestMapping("/api/questions/{questionId}/answers")
public class AnswerApi {

	private final AnswerService answerService;

	public AnswerApi(
		final AnswerService answerService
	) {
		this.answerService = answerService;
	}

	@PostMapping
	ResponseEntity<Void> answer(
		@PathVariable Long questionId,
		@RequestBody @Valid PostAnswerRequest request,
		@CurrentUser String answererId
	) {
		answerService.addAnswer(answererId, questionId, request.getAnswerTitle(), request.getAnswerContent());

		return ResponseEntity.status(HttpStatus.OK).build();
	}

}
