package com.question.answer.application;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.question.answer.domain.Answer;
import com.question.answer.domain.AnswerRepository;
import com.question.question.domain.Question;
import com.question.question.domain.QuestionNotFoundException;
import com.question.question.domain.QuestionRepository;
import com.question.user.domain.User;
import com.question.user.domain.UserNotFoundException;
import com.question.user.domain.UserRepository;

@Service
@Transactional(readOnly = true)
public class AnswerService {

	private final AnswerRepository answerRepository;
	private final UserRepository userRepository;
	private final QuestionRepository questionRepository;

	public AnswerService(
		final AnswerRepository answerRepository,
		final UserRepository userRepository,
		final QuestionRepository questionRepository
	) {
		this.answerRepository = answerRepository;
		this.userRepository = userRepository;
		this.questionRepository = questionRepository;
	}

	@Transactional
	public void addAnswer(final String answererId,
		final Long questionId,
		final String answerTitle,
		final String answerContent) {

		User answerer = userRepository.findById(answererId)
			.orElseThrow(UserNotFoundException::new);

		Question question = questionRepository.findById(questionId)
			.orElseThrow(QuestionNotFoundException::new);

		Answer savedAnswer = answerRepository.save(new Answer(question, answerer, answerTitle, answerContent));

		question.addAnswer(savedAnswer);
	}
}
