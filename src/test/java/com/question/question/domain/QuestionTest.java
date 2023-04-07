package com.question.question.domain;

import com.question.answer.domain.Answer;
import com.question.user.domain.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class QuestionTest {

    private String questionTitle = "블록체인 기업 면접 질문";
    private String questionDetail = "블록체인 기술의 '영지식증명'이 뭔가요?";

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

    @DisplayName("답변은 질문에 포함될 수 있고 내부 내용과 작성자는 변경되지 않아야한다.")
    @Test
    void addAnswer() {
        Answer answer = new Answer(question,
                author,
                "영지식증명에 대해 설명드립니다.",
                "영지식 증명(ZKP)은 증명자가 자신의 지식을 공개하지 않고 그 사실을 검증자에게 증명하는 시스템입니다.");

        question.addAnswer(answer);

        assertThat(question.getAnswers()).hasSize(1);
        assertThat(question.getAnswers().stream().findFirst()).isPresent();

        Answer firstAnswer = question.getAnswers().get(0);

        assertThat(firstAnswer.getAnswerer()).isEqualTo(author);
        assertThat(firstAnswer.getQuestion().getTitle()).isEqualTo(questionTitle);
        assertThat(firstAnswer.getQuestion().getDetail()).isEqualTo(questionDetail);
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

    private Question given(
            String title,
            String detail,
            User author
    ) {
        return new Question(title, detail, author);
    }
}