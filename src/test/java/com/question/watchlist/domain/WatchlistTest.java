package com.question.watchlist.domain;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.question.question.domain.Question;
import com.question.question.domain.QuestionNotFoundException;
import com.question.user.domain.User;

class WatchlistTest {

	@Mock
	private User mockUser;

	String questionTitle;
	String questionDetail;
	Watchlist watchlist;
	Question question;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);

		questionTitle = "Docker에 관한 질문";
		questionDetail = "로컬머신에서 Jenkins를 설치하는 것과 Docker에서 Jenkins 이미지를 받아 컨테이너로 실행하는 것이 뭐가 다른가요??";

		watchlist = new Watchlist(mockUser, "Test Watchlist", "Test Watchlist Description");
		question = new Question(questionTitle, questionDetail, mockUser);

		long heapMaxSize = Runtime.getRuntime().maxMemory();
		long haepFreeSize = Runtime.getRuntime().freeMemory();
		long totalMemory = Runtime.getRuntime().totalMemory();

		System.out.printf("heapMaxSize: %s\nheapFreeSize: %s\ntotalMemory: %s\n\n", heapMaxSize, haepFreeSize,
			totalMemory);
	}

	@Test
	@DisplayName("관심목록의 이름과 설명은 생성자로 인해 바뀔 수 없다.")
	void testWatchlistCreation() {
		// Given
		String name = "Watchlist about Coding";
		String description = "Test Watchlist Description";

		// When
		Watchlist watchlist = new Watchlist(mockUser, name, description);

		// Then
		assertThat(watchlist.getCreator()).isNotNull();
		assertThat(watchlist.getCreator()).isEqualTo(mockUser);
		assertThat(watchlist.getName()).isEqualTo(name);
		assertThat(watchlist.getDescription()).isEqualTo(description);
	}

	@Test
	@DisplayName("관심목록의 제목이 3글자 미만일 때 IllegalArgumentException을 던진다.")
	void testWatchlistCreationWithInvalidName() {
		// Given
		String name = "Te";
		String description = "Test Watchlist Description";

		// When, Then
		assertThrows(IllegalArgumentException.class, () -> new Watchlist(mockUser, name, description));
	}

	@Test
	@DisplayName("관심목록에 포함된 질문은 질문 객체가 가진 제목과 내용과 일치한다.")
	void testAddWatchlist() {
		// When
		watchlist.addWatchlist(question);

		// Then
		assertTrue(watchlist.getQuestionList().contains(question));
		assertThat(watchlist.getQuestionList()).hasSize(1);
		assertThat(watchlist.getQuestionList().stream().findFirst().isPresent()).isTrue();
		Question foundQuestion = watchlist.getQuestionList().stream().findFirst().get();

		assertThat(foundQuestion.getTitle()).isEqualTo(questionTitle);
		assertThat(foundQuestion.getDetail()).isEqualTo(questionDetail);
	}

	@Test
	@DisplayName("존재하지 않는 질문을 관심목록에 포함하면 QuestionNotFoundException이 발생한다.")
	void testAddWatchlistWithNullQuestion() {
		// Given
		Watchlist watchlist = new Watchlist(mockUser, "Test Watchlist", "Test Watchlist Description");

		// When, Then
		assertThrows(QuestionNotFoundException.class, () -> watchlist.addWatchlist(null));
	}

	@Test
	@DisplayName("Watchlist에 포함된 Question 내용은 달라질 수 없다.")
	void testGetters() {
		// Given
		Watchlist watchlist = new Watchlist(mockUser, "Test Watchlist", "Test Watchlist Description");

		Question question1 = new Question(questionTitle, questionDetail, mockUser);
		Question question2 = new Question(questionTitle, questionDetail, mockUser);

		watchlist.addWatchlist(question1);
		watchlist.addWatchlist(question2);

		// When
		Long watchlistId = watchlist.getWatchlistId();
		User creator = watchlist.getCreator();
		Set<Question> resultQuestionList = watchlist.getQuestionList();

		// Then
		assertEquals(watchlistId, watchlist.getWatchlistId());
		assertEquals(mockUser, watchlist.getCreator());
		assertTrue(resultQuestionList.contains(question1));
		assertTrue(resultQuestionList.contains(question2));
	}
}