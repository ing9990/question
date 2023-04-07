package com.question.user.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.question.commons.BaseTimeEntity;
import com.question.question.domain.Question;

@Entity
@Table(name = "user")
public class User extends BaseTimeEntity {

	public static final String DEFAULT_PROFILE_IMAGE = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcS8Ckj7QlRb085_A62q7s7MlGzcQdaIIcfCQApCIvk&s";
	public static final Pattern USERNAME_PATTERN = Pattern.compile("^[A-Za-z0-9]{5,}$");
	public static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,}$");

	@Id
	@Column(name = "user_id", nullable = false)
	private String userId;

	@Column(name = "email", nullable = false, updatable = false)
	private String email;

	@Column(name = "username", nullable = false)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "profile_image_url", length = 1000)
	private String profileImageUrl;

	@Enumerated(EnumType.STRING)
	@Column(name = "user_status", nullable = false)
	private UserStatus userStatus;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "uesr_watchlist")
	private Set<Question> watchlist;

	protected User() {
	}

	public void active() {
		this.userStatus = UserStatus.ACTIVE;
	}

	public void inactive() {
		this.userStatus = UserStatus.INACTIVE;
	}

	public void addWatchlist(final Question question) {
		if (this.watchlist == null) {
			this.watchlist = new HashSet<>();
		}
		this.watchlist.add(question);
	}

	public void updateUsername(final String username) {
		validateUsername(username);
		this.username = username;
	}

	public void updatePassword(final String password) {
		this.password = password;
	}

	public User(final String username,
		final String email,
		final String password,
		final String profileImageUrl) {

		validateEmail(email);
		validateUsername(username);

		this.userId = UUID.randomUUID().toString();
		this.email = email;
		this.username = username;
		this.userStatus = UserStatus.ACTIVE;
		this.password = password;
		this.profileImageUrl =
			profileImageUrl == null || "".equals(profileImageUrl) ? DEFAULT_PROFILE_IMAGE : profileImageUrl;
		this.watchlist = Set.of();
	}

	public User(final String username,
		final String email,
		final String profileImageUrl) {
		this.username = username;
		this.email = email;
		this.profileImageUrl =
			profileImageUrl == null || "".equals(profileImageUrl) ? DEFAULT_PROFILE_IMAGE : profileImageUrl;
	}

	private void validateUsername(final String username) {
		if (!USERNAME_PATTERN.matcher(username).find()) {
			throw new IllegalArgumentException("닉네임은 특수문자가 포함되거나 5글자 보다 적을 수 없습니다.");
		}
	}

	private void validateEmail(final String email) {
		if (!EMAIL_PATTERN.matcher(email).find()) {
			throw new IllegalArgumentException("이메일 형식이 아닙니다.");
		}
	}

	public String getUserId() {
		return this.userId;
	}

	public String getEmail() {
		return this.email;
	}

	public String getUsername() {
		return this.username;
	}

	public String getPassword() {
		return this.password;
	}

	public String getProfileImageUrl() {
		return this.profileImageUrl;
	}

	public UserStatus getUserStatus() {
		return this.userStatus;
	}

	public Set<Question> getWatchlist() {
		return this.watchlist;
	}
}