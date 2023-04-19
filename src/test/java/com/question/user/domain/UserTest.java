package com.question.user.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("User 도메인 검증 테스트")
class UserTest {

	@DisplayName("이메일과 이름을 받아 새로운 User 객체를 생성한다.")
	@Test
	void testNewUser() {
		String username = "testuser";
		String email = "testuser@test.com";
		String password = "testpassword";
		String profileImageUrl = "http://test.com/image.jpg";

		User user = User.userWithAllArgs(username, email, password, profileImageUrl);

		assertThat(user.getUsername()).isEqualTo(username);
		assertThat(user.getEmail()).isEqualTo(email);
		assertThat(user.getPassword()).isEqualTo(password);
		assertThat(user.getProfileImageUrl()).isEqualTo(profileImageUrl);
	}

	@DisplayName("이름이 5글자 미만일 경우 IllegalArgumentException을 던진다.")
	@Test
	void testValidateUsernameLessThan5() {
		String username = "test";

		assertThatIllegalArgumentException()
			.isThrownBy(
				() -> User.userWithAllArgs(username, "testuser@test.com", "testpassword", "http://test.com/image.jpg"));
	}

	@DisplayName("이름에 특수문자가 포함되어 있을 경우 IllegalArgumentException을 던진다.")
	@Test
	void testValidateUsernameContainsSpecialCharacters() {
		String username = "test@user";
		assertThatIllegalArgumentException()
			.isThrownBy(
				() -> User.userWithAllArgs(username, "testuser@test.com", "testpassword", "http://test.com/image.jpg"));
	}

	@DisplayName("올바른 형식의 이메일을 입력할 경우 새로운 User 객체를 생성한다.")
	@Test
	void testValidateEmail() {
		String username = "testuser";
		String email = "testuser@test.com";
		String password = "testpassword";
		String profileImageUrl = "http://test.com/image.jpg";

		User user = User.userWithAllArgs(username, email, password, profileImageUrl);

		assertThat(user.getUsername()).isEqualTo(username);
		assertThat(user.getEmail()).isEqualTo(email);
		assertThat(user.getPassword()).isEqualTo(password);
		assertThat(user.getProfileImageUrl()).isEqualTo(profileImageUrl);
	}

	@DisplayName("잘못된 형식의 이메일을 입력할 경우 IllegalArgumentException을 던진다.")
	@Test
	void testValidateInvalidEmail() {
		String email = "invalidemail";
		assertThatIllegalArgumentException()
			.isThrownBy(() -> User.userWithAllArgs("testuser", email, "testpassword", "http://test.com/image.jpg"));
	}
}