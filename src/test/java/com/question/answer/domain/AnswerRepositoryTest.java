package com.question.answer.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.question.question.domain.Question;
import com.question.user.domain.User;

@SpringBootTest
@ActiveProfiles("test")
@Transactional
public class AnswerRepositoryTest {

	@Autowired
	private AnswerRepository answerRepository;

	private Question question;

	private Answer answer;

	private User answerer;

	private User author;

	String title = "테스트 답변 제목";
	String content = "테스트 답변 내용";

	@BeforeEach
	public void setUp() {
		author = new User("testAuthor", "question.author@test.com", "test_password", "test_image.png");
		answerer = new User("testAnswerer", "question.answerer2@test.com", "test_password", "test_answerer.png");
		question = new Question("테스트 질문 제목입니다.", "테스트 질문글입니다.", author);
		answer = new Answer(question, answerer, title, content);
	}

	@Test
	@DisplayName("유저의 답변을 저장할 수 있다.")
	public void saveAnswer() {
		System.out.println(answerRepository.findAll());
		System.out.println(answer);

		// given
		Answer savedAnswer = answerRepository.save(answer);

		// when
		Answer foundAnswer = answerRepository.findById(savedAnswer.getAnswerId()).orElse(null);

		// then
		assertThat(foundAnswer).isNotNull();
		assertThat(foundAnswer.getAnswerId()).isEqualTo(savedAnswer.getAnswerId());
		assertThat(foundAnswer.getTitle()).isEqualTo(answer.getTitle());
		assertThat(foundAnswer.getContent()).isEqualTo(answer.getContent());
	}

	@Test
	public void updateAnswer() {
		// given
		Answer savedAnswer = answerRepository.save(answer);

		// when
		savedAnswer.updateTitle("수정된 답변 제목");
		savedAnswer.updateContent("수정된 답변 내용");
		Answer updatedAnswer = answerRepository.save(savedAnswer);

		// then
		assertThat(updatedAnswer).isNotNull();
		assertThat(updatedAnswer.getAnswerId()).isEqualTo(savedAnswer.getAnswerId());
		assertThat(updatedAnswer.getTitle()).isEqualTo("수정된 답변 제목");
		assertThat(updatedAnswer.getContent()).isEqualTo("수정된 답변 내용");
	}

	@Test
	public void deleteAnswer() {
		// given
		Answer savedAnswer = answerRepository.save(answer);

		// when
		answerRepository.delete(savedAnswer);

		// then
		Answer deletedAnswer = answerRepository.findById(savedAnswer.getAnswerId()).orElse(null);
		assertThat(deletedAnswer).isNull();
	}
}