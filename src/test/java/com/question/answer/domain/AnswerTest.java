package com.question.answer.domain;

import com.question.question.domain.Question;
import com.question.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class AnswerTest {

    private static final String questionTitle = "블록체인 기업 면접 질문";
    private static final String questionDetail = "블록체인 기술의 '영지식증명'이 뭔가요?";

    private User author;
    private Question question;

    @BeforeEach
    public void each() {
        author = new User("blockchain",
                "blockchain@gmail.com",
                "testpassword",
                "https://testimage/image.png");

        question = new Question(questionTitle,
                questionDetail,
                author);
    }

    private Answer givenWithField(String title, String content) {
        return new Answer(question, author, title, content);
    }


    @DisplayName("답변의 제목은 빈 칸이거나 null이라면 IllegalArgumentException을 던진다.")
    @Test
    void answerTitleIsNotNull() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Answer(question, author, "", "test-detail"));
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Answer(question, author, null, "test-detail"));
    }

    @DisplayName("답변의 내용은 빈 칸이나 null이라면 IllegalArgumentException을 던진다.")
    @Test
    void answerDetailIsNotNull() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Answer(question, author, "test-title", ""));
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Answer(question, author, "test-title", null));
    }

    @DisplayName("답변의 부모 도메인인 Question 도메인이 null이라면 IllegalArgumentException을 던진다.")
    @Test
    void questionIsNotNull() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Answer(null, author, "test-title", "test-content"));
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Answer(null, author, "test-title", "test-content"));
    }

    @DisplayName("답변자가 null이라면 IllegalArgumentException을 던진다.")
    @Test
    void answererIsNotNull() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Answer(question, null, "test-title", "test-content"));
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Answer(question, null, "test-title", "test-content"));
    }

    @DisplayName("답변의 본문은 수정할 수 있다.")
    @Test
    void updateAnswerDetail() {
        // given
        Answer answer = givenWithField("test-title", "test-content");

        // when
        answer.updateContent("updated-content");

        // then
        assertThat(answer.getTitle()).isEqualTo("test-title");
        assertThat(answer.getContent()).isEqualTo("updated-content");
    }

    @DisplayName("답변의 제목은 수정할 수 있다.")
    @Test
    void updateAnswerTitle() {
        // given
        Answer answer = givenWithField("test-title", "test-content");

        // when
        answer.updateTitle("updated-title");

        // then
        assertThat(answer.getTitle()).isEqualTo("updated-title");
        assertThat(answer.getContent()).isEqualTo("test-content");
    }

    @DisplayName("제목이나 본문을 수정하면서 빈 값이 들어가면 IllegalArgumentException을 던진다.")
    @Test
    void answerTitleAndContentIsNotNull() {
        Answer answer = givenWithField("test-title", "test-content");

        assertThatIllegalArgumentException()
                .isThrownBy(() -> answer.updateTitle(""));

        assertThatIllegalArgumentException()
                .isThrownBy(() -> answer.updateContent(""));
    }

}