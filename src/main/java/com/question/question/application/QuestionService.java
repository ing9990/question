package com.question.question.application;

import com.question.commons.error.exception.UserNotFoundException;
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
    public void saveQuestion(final String title, final String detail, final String userId) {
        var user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);

        questionRepository.save(new Question(title, detail, user));
    }
}
