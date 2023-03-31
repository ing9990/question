package com.question.user.application;

import com.question.user.domain.User;
import com.question.user.domain.UserNotFoundException;
import com.question.user.domain.UserRepository;
import com.question.user.io.response.UserResponse;
import com.question.user.io.request.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final UserRepository userRepository;

    public UserResponse findById(final String id) {
        return UserResponse.of(userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new));
    }

    @Transactional
    public void updateUsername(final String id, final UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        user.updateUsername(request.getUsername());
    }
}
