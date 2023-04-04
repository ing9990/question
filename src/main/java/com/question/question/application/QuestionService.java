package com.question.question.application;

import com.question.question.io.response.QuestionAndAnswersResponse;
import com.question.question.io.response.QuestionResponse;
import com.question.question.domain.QuestionNotFoundException;
import com.question.user.domain.UserNotFoundException;
import com.question.question.domain.Question;
import com.question.question.domain.QuestionRepository;
import com.question.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    @Transactional
    public void saveQuestion(final String userId, final String title, final String detail) {
        var user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        questionRepository.save(new Question(title, detail, user));
    }

    public QuestionResponse getQuestion(final Long questionId) {
        return QuestionResponse.of(questionRepository.findById(questionId)
                .orElseThrow(QuestionNotFoundException::new));
    }

    public QuestionAndAnswersResponse getQuestionAndAnswers(
            final Long questionId
    ) {
        var question = questionRepository.findById(questionId)
                .orElseThrow(QuestionNotFoundException::new);

        return QuestionAndAnswersResponse.of(question);
    }

    public List<QuestionResponse> getQuestions(Pageable pageable) {
        return questionRepository.findAll(pageable)
                .stream().map(QuestionResponse::of)
                .collect(Collectors.toList());
    }
}
