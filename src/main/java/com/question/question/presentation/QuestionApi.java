package com.question.question.presentation;

import com.question.answer.io.response.QuestionAnswerResponse;
import com.question.infra.in.aop.support.CurrentUser;
import com.question.question.application.QuestionService;
import com.question.question.io.request.CreateQuestionRequest;
import com.question.question.io.response.QuestionAndAnswersResponse;
import com.question.question.io.response.QuestionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/questions")
@RequiredArgsConstructor
public class QuestionApi {

    private final QuestionService questionService;

    /**
     * 질문글의 제목과 내용을 리턴합니다.
     *
     * @param questionId
     * @return
     */
    @GetMapping("/{questionId}")
    public ResponseEntity<QuestionResponse> getQuestion(
            @PathVariable Long questionId
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(questionService.getQuestion(questionId));
    }

    /**
     * 질문글과 답변글을 모두 리턴합니다.
     *
     * @param questionId
     * @return
     */
    @GetMapping("/{questionId}/answers")
    public ResponseEntity<QuestionAndAnswersResponse> getQuestionAndAnswers(
            @PathVariable Long questionId
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(questionService.getQuestionAndAnswers(questionId));
    }

    /**
     * 새로운 질문글을 작성합니다.
     *
     * @param createQuestionRequest
     * @param userId
     * @return
     */
    @CurrentUser
    @PostMapping
    ResponseEntity<Void> makeQuestion(
            @RequestBody @Valid CreateQuestionRequest createQuestionRequest,
            String userId
    ) {
        questionService.saveQuestion(
                userId,
                createQuestionRequest.getTitle(),
                createQuestionRequest.getDetail()
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
