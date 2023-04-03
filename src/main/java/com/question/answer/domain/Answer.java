package com.question.answer.domain;

import com.question.commons.BaseTimeEntity;
import com.question.question.domain.Question;
import com.question.user.domain.User;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

    @ManyToOne
    @JoinColumn(name = "question_id", nullable = false)
    private Question question;

    @OneToOne
    @JoinColumn(name = "answerer_id", nullable = false)
    private User answerer;

    @Column(name = "answer_title", nullable = false)
    private String title;

    @Column(name = "answer_content", nullable = false)
    private String content;
}
