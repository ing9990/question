package com.question.watchlist.application;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.question.auth.domain.InvalidAuthenticationException;
import com.question.commons.NotificationFormat;
import com.question.infra.out.io.NotificationEvent;
import com.question.infra.out.io.NotificationType;
import com.question.question.domain.Question;
import com.question.question.domain.QuestionNotFoundException;
import com.question.question.domain.QuestionRepository;
import com.question.user.domain.User;
import com.question.user.domain.UserNotFoundException;
import com.question.user.domain.UserRepository;
import com.question.watchlist.domain.Watchlist;
import com.question.watchlist.domain.WatchlistNotFoundException;
import com.question.watchlist.domain.WatchlistRepository;
import com.question.watchlist.presentation.io.response.WatchlistResponse;

@Service
@Transactional(readOnly = true)
public class WatchlistService {

	private final ApplicationEventPublisher publisher;
	private final WatchlistRepository watchlistRepository;
	private final QuestionRepository questionRepository;
	private final UserRepository userRepository;

	public WatchlistService(
		final WatchlistRepository watchlistRepository,
		final QuestionRepository questionRepository,
		final ApplicationEventPublisher publisher,
		UserRepository userRepository) {
		this.watchlistRepository = watchlistRepository;
		this.questionRepository = questionRepository;
		this.publisher = publisher;
		this.userRepository = userRepository;
	}

	public List<WatchlistResponse> getWatchlist(final String userId) {
		List<Watchlist> watchlist = watchlistRepository.findWatchlistByCreatorId(userId);

		return watchlist.stream().map(WatchlistResponse::from)
			.collect(Collectors.toList());
	}

	@Transactional
	public void addWatchlist(final String userId,
		final Long watchlistId,
		final Long questionId) {

		Watchlist watchlist = watchlistRepository.findById(watchlistId)
			.orElseThrow(WatchlistNotFoundException::new);

		Question question = questionRepository.findById(questionId)
			.orElseThrow(QuestionNotFoundException::new);

		User watchlistCreator = watchlist.getCreator();

		checkWatchlistCreator(watchlist, userId);
		checkDuplicateQuestion(watchlist, question);

		watchlist.addWatchlist(question);

		publisher.publishEvent(new NotificationEvent(
			this,
			NotificationType.EMAIL,
			getMessage(watchlistCreator.getUsername(), question.getTitle()),
			getPushToken(watchlistCreator),
			watchlistCreator.getEmail()
		));
	}

	private String getPushToken(User watchlistCreator) {
		return "testpush";
	}

	private String getMessage(String creatorUsername, String title) {
		return String.format(NotificationFormat.ADD_WATCHLIST_FORMAT.getFormat(), creatorUsername, title);
	}

	private void checkWatchlistCreator(Watchlist watchlist, String userId) {
		if (!userId.equals(watchlist.getCreator().getUserId())) {
			throw new InvalidAuthenticationException();
		}
	}

	@Transactional
	public void newWatchlist(final String userId,
		final String watchlistName,
		final String watchlistDescription) {

		User creator = userRepository.findById(userId)
			.orElseThrow(UserNotFoundException::new);

		watchlistRepository.save(
			new Watchlist(creator, watchlistName, watchlistDescription)
		);
	}

	private void checkDuplicateQuestion(final Watchlist watchlist, final Question question) {
		if (watchlist.getQuestionList().contains(question)) {
			throw new IllegalArgumentException("이미 관심목록에 포함된 질문입니다.");
		}
	}
}