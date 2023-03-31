package com.question.user.io.request;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserUpdateRequest {

    @NotBlank(message = "회원 이름이 공백일 수 없습니다.")
    private String username;

}
