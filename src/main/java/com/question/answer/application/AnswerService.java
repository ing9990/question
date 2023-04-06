package com.question.answer.application;

import com.question.answer.domain.Answer;
import com.question.answer.domain.AnswerRepository;
import com.question.answer.event.AnsweredEvent;
import com.question.question.domain.QuestionNotFoundException;
import com.question.question.domain.QuestionRepository;
import com.question.user.domain.UserNotFoundException;
import com.question.user.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AnswerService {

    private final ApplicationEventPublisher publisher;
    private final AnswerRepository answerRepository;
    private final UserRepository userRepository;
    private final QuestionRepository questionRepository;

    @Transactional
    public void addAnswer(final String answererId,
                          final Long questionId,
                          final String answerTitle,
                          final String answerContent) {

        var answerer = userRepository.findById(answererId)
                .orElseThrow(UserNotFoundException::new);

        var question = questionRepository.findById(questionId)
                .orElseThrow(QuestionNotFoundException::new);

        var savedAnswer = answerRepository.save(new Answer(question, answerer, answerTitle, answerContent));

        question.addAnswer(savedAnswer);

        try {
            publisher.publishEvent(new AnsweredEvent(this,
                    answerer.getUserId(),
                    answerer.getUsername(),
                    question.getTitle(),
                    answerer.getEmail(),
                    question.getAuthor().getEmail()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
