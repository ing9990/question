package com.question.question.presentation;

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

    @PostMapping
        //@LoginRequired
        // LoginRequired 에서 로그인 정보 확인과 계정이 활동상태인지 체크
    ResponseEntity<Void> makeQuestion(
            @RequestBody @Valid CreateQuestionRequest createQuestionRequest,
            String userId
    ) {
        questionService.saveQuestion(
                createQuestionRequest.getTitle(),
                createQuestionRequest.getDetail(),
                userId
        );
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
