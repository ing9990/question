package com.question.question.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.util.StringUtils;

import com.question.answer.domain.Answer;
import com.question.commons.BaseTimeEntity;
import com.question.user.domain.User;

@Entity
@Table(name = "question")
public class Question extends BaseTimeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "question_id")
	private Long questionId;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "question_author", nullable = false)
	private User author;

	@Column(name = "question_title", nullable = false, length = 30, updatable = false)
	private String title;

	@Column(name = "question_detail", nullable = false, length = 255)
	private String detail;

	@OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Answer> answers = new ArrayList<>();

	public Question(
		String title,
		String detail,
		User author
	) {
		validateTitle(title);
		validateDetail(detail);
		validateAuthor(author);

		this.title = title;
		this.detail = detail;
		this.author = author;
		this.answers = new ArrayList<>();
	}

	protected Question() {
	}

	public static QuestionBuilder builder() {
		return new QuestionBuilder();
	}

	public void updateDetail(String detail) {
		validateDetail(detail);

		this.detail = detail;
	}

	public void addAnswer(Answer answer) {
		if (this.answers == null) {
			this.answers = new ArrayList<>();
		}
		this.answers.add(answer);
	}

	private void validateAuthor(User author) {
		if (author == null) {
			throw new IllegalArgumentException("질문자를 찾을 수 없습니다.");
		}
	}

	private void validateDetail(String detail) {
		if ((!StringUtils.hasText(detail)) || detail.length() < 5) {
			throw new IllegalArgumentException("질문글의 본문은 최소 5글자 이상입니다.");
		}
	}

	private void validateTitle(String title) {
		if ((!StringUtils.hasText(title)) || title.length() < 3) {
			throw new IllegalArgumentException("질문글의 제목은 최소 3글자 이상입니다.");
		}
	}

	public boolean isAuthor(String userId) {
		return this.author.getUserId().equals(userId);
	}

	public Long getQuestionId() {
		return this.questionId;
	}

	public User getAuthor() {
		return this.author;
	}

	public String getTitle() {
		return this.title;
	}

	public String getDetail() {
		return this.detail;
	}

	public List<Answer> getAnswers() {
		return this.answers;
	}

	public static class QuestionBuilder {
		private String title;
		private String detail;
		private User author;

		QuestionBuilder() {
		}

		public QuestionBuilder title(String title) {
			this.title = title;
			return this;
		}

		public QuestionBuilder detail(String detail) {
			this.detail = detail;
			return this;
		}

		public QuestionBuilder author(User author) {
			this.author = author;
			return this;
		}

		public Question build() {
			return new Question(title, detail, author);
		}

		public String toString() {
			return "Question.QuestionBuilder(title=" + this.title + ", detail=" + this.detail + ", author="
				+ this.author
				+ ")";
		}
	}
}
