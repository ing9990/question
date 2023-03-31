package com.question.question.domain;

import com.question.commons.BaseTimeEntity;
import com.question.user.domain.User;
import lombok.*;

import javax.persistence.*;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "question")
public class Question extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "question_id")
    private Long questionId;

    @OneToOne
    @JoinColumn(name = "question_author", nullable = false)
    private User author;

    @Column(name = "question_title", nullable = false, length = 30, updatable = false)
    private String title;

    @Column(name = "question_detail", nullable = false, length = 255)
    private String detail;

    @Builder
    public Question(
            String title,
            String detail,
            User author
    ) {
        this.title = title;
        this.detail = detail;
        this.author = author;
    }
}
