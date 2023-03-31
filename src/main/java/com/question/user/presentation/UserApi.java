package com.question.user.presentation;

import com.question.auth.io.LoginUser;
import com.question.user.application.UserService;
import com.question.user.io.request.UserUpdateRequest;
import com.question.user.io.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserResponse> findMe(@AuthenticationPrincipal final LoginUser loginUser) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(userService.findById(loginUser.getUserId()));
    }

    @PatchMapping
    public ResponseEntity<Void> updateUsername(@AuthenticationPrincipal final LoginUser loginUser,
                                               @Valid @RequestBody final UserUpdateRequest request) {
        userService.updateUsername(loginUser.getUserId(), request);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
