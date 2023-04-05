package com.question.question.domain;

import com.question.user.domain.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class QuestionTest {

    private Question given(
            String title,
            String detail,
            User author
    ) {
        return new Question(title, detail, author);
    }

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


    @DisplayName("질문자를 찾을 수 없다면 IllegalArgumentExcpetion을 던진다.")
    @Test
    void testAuthorIsNull() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> given("test-title", "test-detail", null));
    }


    @DisplayName("질문의 제목이 3글자 미만이라면 IllegalArgumentExcpetion을 던진다.")
    @Test
    void testQuestionTitleLessThan3() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> given("i", "test-detail", null));
    }

    @DisplayName("질문의 본문이 5글자 미만이라면 IllegalArgumentExcpetion을 던진다.")
    @Test
    void testQuestionDetailLessThan5() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> given("test-title", "i", null));
    }

    @DisplayName("질문의 작성자가 맞다면 True를 반환한다.")
    @Test
    void returnTrueIfSameAuthor() {
        //given
        User user = new User("username", "email@email.com", "password", "");
        Question question = given("test-title", "test-detail", user);

        assertThat(question.isAuthor(user.getUserId())).isTrue();
    }
}