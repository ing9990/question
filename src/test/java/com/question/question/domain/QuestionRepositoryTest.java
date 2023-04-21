package com.question.question.domain;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.question.answer.domain.Answer;
import com.question.answer.domain.AnswerRepository;
import com.question.user.domain.User;
import com.question.user.domain.UserRepository;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
class QuestionRepositoryTest {

	public static final String TITLE = "도메인 테스트 작성 중 질문이 있어 글 남깁니다.";
	public static final String CONTENT = "테스트하기 쉬운 코드를 만드려면 어떻게 해야될까요?? 고수분들의 답변을 기다리겠습니다.";

	public static final String ANSWER_TITLE = "테스트하기 쉬운 코드를 작성하기 위해서는 의존성을 최소화 해야됩니다.";
	public static final String ANSWER_DETAIL = "도메인이 외부와 의존관계를 가지지 않으면서 상태성을 가지는 로직을 최소화 해야됩니다. 또한 port-adapter(헥사고날) 아키텍처나 도메인 주도 개발을 공부하는 것도 좋은 방법입니다.";

	@Autowired
	private QuestionRepository questionRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AnswerRepository answerRepository;

	@BeforeEach
	void each() {
		User questionAuthor = User.userWithAllArgs("testauthor", "testauthor@author.com", "testpassword",
			"testimage.png");

		Question testQuestion = new Question(TITLE, CONTENT, questionAuthor);

		userRepository.save(questionAuthor);
		questionRepository.save(testQuestion);

		Answer answer1 = answerRepository.save(new Answer(testQuestion, questionAuthor, ANSWER_TITLE, ANSWER_DETAIL));
		Answer answer2 = answerRepository.save(new Answer(testQuestion, questionAuthor, ANSWER_TITLE, ANSWER_DETAIL));
		Answer answer3 = answerRepository.save(new Answer(testQuestion, questionAuthor, ANSWER_TITLE, ANSWER_DETAIL));
		Answer answer4 = answerRepository.save(new Answer(testQuestion, questionAuthor, ANSWER_TITLE, ANSWER_DETAIL));

		testQuestion.addAnswer(answer1);
		testQuestion.addAnswer(answer2);
		testQuestion.addAnswer(answer3);
		testQuestion.addAnswer(answer4);
	}

	@DisplayName("모든 질문을 조회한다.")
	@Test
	void findAll() {
		List<Question> findAll = questionRepository.findAll();
		assertThat(findAll.size()).isEqualTo(1);
	}

	@DisplayName("답글이 N개 이상 있는 질문을 조회한다.")
	@Test
	void findQuestionsByAnswers() {
		int n = 3;
		List<Question> result = questionRepository.findQuestionsByAnswersSize(n);
		assertThat(result.size()).isEqualTo(1);
	}

	@DisplayName("질문을 저장했을 때 질문 제목이나 본문 글이 변경될 수 없다.")
	@Test
	void 질문_저장_조회_테스트() {
		User testAuthor = User.userWithAllArgs("testuser", "testuser@test.com", "testpassword", "test.test.png");
		userRepository.save(testAuthor);

		Question testQuestion = new Question("test_title", "test_detail", testAuthor);
		questionRepository.save(testQuestion);

		Question findById = questionRepository.findById(testQuestion.getQuestionId())
			.orElseThrow(QuestionNotFoundException::new);

		// assertThat(findById.getTitle()).isEqualTo(TITLE);
		// assertThat(findById.getDetail()).isEqualTo(CONTENT);
		// assertThat(findById.getAuthor()).isEqualTo(questionAuthor);

	}
}










