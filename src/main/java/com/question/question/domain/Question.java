package com.question.question.domain;

import com.question.answer.domain.Answer;
import com.question.commons.BaseTimeEntity;
import com.question.user.domain.User;
import lombok.*;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    @Builder
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
}
