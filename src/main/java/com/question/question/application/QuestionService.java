package com.question.question.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.question.auth.domain.InvalidAuthenticationException;
import com.question.question.domain.Question;
import com.question.question.domain.QuestionNotFoundException;
import com.question.question.domain.QuestionRepository;
import com.question.question.presentation.io.response.QuestionAndAnswersResponse;
import com.question.question.presentation.io.response.QuestionResponse;
import com.question.user.domain.User;
import com.question.user.domain.UserNotFoundException;
import com.question.user.domain.UserRepository;

@Service
@Transactional(readOnly = true)
public class QuestionService {

	private final QuestionRepository questionRepository;
	private final UserRepository userRepository;

	public QuestionService(
		final QuestionRepository questionRepository,
		final UserRepository userRepository
	) {
		this.questionRepository = questionRepository;
		this.userRepository = userRepository;
	}

	@Transactional
	public void saveQuestion(final String userId, final String title, final String detail) {
		User user = userRepository.findById(userId)
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
		Question question = questionRepository.findById(questionId)
			.orElseThrow(QuestionNotFoundException::new);

		return QuestionAndAnswersResponse.of(question);
	}

	public List<QuestionResponse> getQuestions(Pageable pageable) {
		return questionRepository.findAll(pageable)
			.stream().map(QuestionResponse::of)
			.collect(Collectors.toList());
	}

	@Transactional
	public void updateDetail(Long questionId, String userId, String detail) {
		Question question = questionRepository.findById(questionId)
			.orElseThrow(QuestionNotFoundException::new);

		if (question.isAuthor(userId)) {
			throw new InvalidAuthenticationException();
		}

		question.updateDetail(detail);
	}

	@Transactional
	public void deleteQuestion(Long questionId, String userId) {
		Question question = questionRepository.findById(questionId)
			.orElseThrow(QuestionNotFoundException::new);

		if (question.isAuthor(userId)) {
			throw new InvalidAuthenticationException();
		}

		questionRepository.deleteById(questionId);
	}
}
