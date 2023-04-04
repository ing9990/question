package com.question.answer.domain;

import com.question.commons.BaseTimeEntity;
import com.question.question.domain.Question;
import com.question.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(name = "answer")
public class Answer extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answer_id")
    private Long answerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "answerer_id", nullable = false)
    private User answerer;

    @Column(name = "answer_title", nullable = false)
    private String title;

    @Column(name = "answer_content", nullable = false)
    private String content;


    public Answer(Question question, User answerer, String title, String content) {
        validateQuestion(question);
        validateAnswerer(answerer);
        validateTitle(title);
        validateContent(content);

        this.question = question;
        this.answerer = answerer;
        this.title = title;
        this.content = content;
    }

    public void updateTitle(String title) {
        validateTitle(title);
        this.title = title;
    }

    public void updateContent(String content) {
        validateContent(content);
        this.content = content;
    }

    private void validateContent(String content) {
        if (!StringUtils.hasText(content)) {
            throw new IllegalArgumentException("답변글의 내용은 빈 값일 수 없습니다.");
        }
    }

    private void validateTitle(String title) {
        if (!StringUtils.hasText(title)) {
            throw new IllegalArgumentException("답변글의 제목은 빈 값일 수 없습니다.");
        }

        if (title.length() < 3) {
            throw new IllegalArgumentException("답변글의 제목은 3글자 이상이어야 합니다.");
        }
    }

    private void validateAnswerer(User answerer) {
        if (answerer == null) {
            throw new IllegalArgumentException("답변자를 찾을 수 없습니다.");
        }
    }

    private void validateQuestion(Question question) {
        if (question == null) {
            throw new IllegalArgumentException("질문글을 찾을 수 없습니다.");
        }
    }
}
