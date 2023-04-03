package com.question.question.application;

import com.question.question.io.response.QuestionResponse;
import com.question.question.domain.QuestionNotFoundException;
import com.question.user.domain.UserNotFoundException;
import com.question.question.domain.Question;
import com.question.question.domain.QuestionRepository;
import com.question.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
