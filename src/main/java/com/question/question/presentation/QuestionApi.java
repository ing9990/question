package com.question.question.presentation;

import javax.validation.Valid;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.question.commons.BaseResponse;
import com.question.infra.in.aop.support.CurrentUser;
import com.question.question.application.QuestionService;
import com.question.question.presentation.io.request.CreateQuestionRequest;
import com.question.question.presentation.io.request.UpdateDetailRequest;

@RestController
@RequestMapping("/api/questions")
public class QuestionApi {

	private final QuestionService questionService;

	public QuestionApi(
		final QuestionService questionService
	) {
		this.questionService = questionService;
	}

	@GetMapping
	public ResponseEntity<BaseResponse> getQuestionsByPage(
		@PageableDefault(size = 30) Pageable pageable
	) {
		return ResponseEntity.status(HttpStatus.OK)
			.body(BaseResponse.ok(questionService.getQuestions(pageable), "질문글 목록을 조회합니다."));
	}

	/**
	 * 질문글의 제목과 내용을 리턴합니다.
	 *
	 * @param questionId
	 * @return
	 */
	@GetMapping("/{questionId}")
	public ResponseEntity<BaseResponse> getQuestion(
		@PathVariable Long questionId
	) {
		return ResponseEntity.status(HttpStatus.OK)
			.body(BaseResponse.ok(questionService.getQuestion(questionId), "질문글의 제목과 내용을 응답합니다."));
	}

	/**
	 * 질문글과 답변글을 모두 리턴합니다.
	 *
	 * @param questionId
	 * @return
	 */
	@GetMapping("/{questionId}/answers")
	public ResponseEntity<BaseResponse> getQuestionAndAnswers(
		@PathVariable Long questionId
	) {
		return ResponseEntity.status(HttpStatus.OK)
			.body(BaseResponse.ok(questionService.getQuestionAndAnswers(questionId), "질문과 답변글을 모두 응답합니다."));
	}

	/**
	 * 새로운 질문글을 작성합니다.
	 *
	 * @param createQuestionRequest
	 * @param userId
	 * @return
	 */
	@PostMapping
	ResponseEntity<Void> makeQuestion(
		@RequestBody @Valid CreateQuestionRequest createQuestionRequest,
		@CurrentUser String userId
	) {
		questionService.saveQuestion(
			userId,
			createQuestionRequest.getTitle(),
			createQuestionRequest.getDetail()
		);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PatchMapping("/{questionId}")
	public ResponseEntity<Void> updateDetail(
		@Valid @RequestBody UpdateDetailRequest updateDetailRequest,
		@PathVariable Long questionId,
		@CurrentUser String userId
	) {
		questionService.updateDetail(questionId, userId, updateDetailRequest.getDetail());
		return ResponseEntity.status(HttpStatus.OK).build();
	}

	@DeleteMapping("/{questionId}")
	public ResponseEntity<Void> deleteQuestion(
		@PathVariable Long questionId,
		@CurrentUser String userId
	) {
		questionService.deleteQuestion(questionId, userId);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	}
}
