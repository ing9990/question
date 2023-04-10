package com.question.watchlist.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.util.StringUtils;

import com.question.commons.BaseTimeEntity;
import com.question.question.domain.Question;
import com.question.question.domain.QuestionNotFoundException;
import com.question.user.domain.User;

@Entity
@Table(name = "watchlist")
public class Watchlist extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "watchlist_id")
	private Long watchlistId;

	@OneToOne
	@JoinColumn(name = "watchlist_creator_id", nullable = false)
	private User creator;

	@Column(name = "watchlist_name", nullable = false)
	private String name;

	@Column(name = "watchlist_description")
	private String description;

	@OneToMany
	@JoinTable(name = "watchlist_contents",
		joinColumns = @JoinColumn(name = "watchlist_id", nullable = false, updatable = false),
		inverseJoinColumns = @JoinColumn(name = "question_id", nullable = false)
	)
	private Set<Question> questionList;

	protected Watchlist() {

	}

	public Watchlist(
		final User creator,
		final String name,
		final String description
	) {
		validateWatchlistName(name);

		this.creator = creator;
		this.questionList = new HashSet<>();
		this.name = name;
		this.description = description;
	}

	private void validateWatchlistName(String name) {
		if (!StringUtils.hasText(name) || name.length() < 3) {
			throw new IllegalArgumentException("관심목록의 제목은 3 글자 이상입니다.");
		}
	}

	public void addWatchlist(
		final Question question
	) {
		validationWatchlist();
		validationQuestion(question);

		this.questionList.add(question);
	}

	private void validationQuestion(
		final Question question
	) {
		if (question == null) {
			throw new QuestionNotFoundException();
		}
	}

	private void validationWatchlist() {
		if (this.questionList == null) {
			this.questionList = new HashSet<>();
		}
	}

	public Long getWatchlistId() {
		return watchlistId;
	}

	public User getCreator() {
		return creator;
	}

	public Set<Question> getQuestionList() {
		return questionList;
	}
}