package com.question.user.presentation;

import com.question.infra.in.aop.support.CurrentUser;
import com.question.user.application.UserService;
import com.question.user.io.request.SignUpRequest;
import com.question.user.io.request.UserUpdateRequest;
import com.question.user.io.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApi {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<UserResponse> findMe(@CurrentUser final String userId) {
        return ResponseEntity.status(HttpStatus.OK).body(userService.findById(userId));
    }


    @PostMapping
    public ResponseEntity<Void> singUp(
            @RequestBody @Valid SignUpRequest signUpRequest
    ) {
        userService.save(
                signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                signUpRequest.getPassword(),
                signUpRequest.getProfileImageUrl()
        );

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @CurrentUser
    @PatchMapping
    public ResponseEntity<Void> updateUsername(
            @Valid @RequestBody final UserUpdateRequest request,
            final String userId
    ) {
        userService.updateUsername(userId, request);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
