package com.question.question.presentation;

import com.question.infra.in.aop.support.CurrentUser;
import com.question.question.application.QuestionService;
import com.question.question.io.request.CreateQuestionRequest;
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

    @GetMapping("/{questionId}")
    public ResponseEntity<QuestionResponse> getQuestion(
            @PathVariable Long questionId
    ) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(questionService.getQuestion(questionId));
    }

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
