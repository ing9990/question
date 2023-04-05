package com.question.answer.presentation;

import com.question.answer.application.AnswerService;
import com.question.answer.io.request.PostAnswerRequest;
import com.question.infra.in.aop.support.CurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/questions/{questionId}/answers")
@RequiredArgsConstructor
public class AnswerApi {

    private final AnswerService answerService;

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
