package com.question.user.application;

import com.question.user.domain.DuplicateUsernameException;
import com.question.user.domain.User;
import com.question.user.domain.UserNotFoundException;
import com.question.user.domain.UserRepository;
import com.question.user.io.response.UserResponse;
import com.question.user.io.request.UserUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {

    private final PasswordEncoder encoder;
    private final UserRepository userRepository;

    public UserResponse findById(final String id) {
        return UserResponse.of(userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new));
    }

    @Transactional
    public void updateUsername(final String id, final UserUpdateRequest request) {
        hasSameUsername(request.getUsername());

        User user = userRepository.findById(id)
                .orElseThrow(UserNotFoundException::new);

        user.updateUsername(request.getUsername());
    }

    @Transactional
    public void save(String username, String email, String password, String profileImageUrl) {
        userRepository.save(new User(username, email, encoder.encode(password), profileImageUrl));
    }

    private void hasSameUsername(String username) {
        if (userRepository.findUserByUsername(username).isPresent()) {
            throw new DuplicateUsernameException();
        }
    }

    public void hasSameEamil(String email) {
        if (userRepository.findUserByEmail(email).isPresent()) {
            throw new DuplicateEmailException(email);
        }
    }
}
