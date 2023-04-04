package com.question.user.io.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpRequest {

    public static final String USERNAME_PATTERN = "^[A-Za-z]{5,}$";

    @javax.validation.constraints.Pattern(regexp = USERNAME_PATTERN,
            message ="닉네임은 특수문자가 포함되거나 5글자 보다 작을 수 없습니다.")
    private String username;

    @Email(message = "이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "패스워드가 빈 값입니다.")
    private String password;

    private String profileImageUrl;

}
