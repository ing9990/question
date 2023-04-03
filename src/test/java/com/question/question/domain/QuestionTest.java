package com.question.question.domain;

import com.question.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QuestionTest {

    @DisplayName("Question 객체 생성 테스트")
    @Test
    void createQuestion() {
        // given
        String title = "테스트 질문 제목";
        String detail = "테스트 질문 내용";
        User author = new User("testuser", "test_user@test.com", "test_password", "https://helloworld.png");

        // when
        Question question = Question.builder()
                .title(title)
                .detail(detail)
                .author(author)
                .build();

        // then
        assertThat(question).isNotNull();
        assertThat(question.getTitle()).isEqualTo(title);
        assertThat(question.getDetail()).isEqualTo(detail);
        assertThat(question.getAuthor()).isEqualTo(author);
    }
}