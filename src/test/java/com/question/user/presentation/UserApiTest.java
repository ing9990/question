package com.question.user.presentation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.question.auth.application.JwtTokenProvider;
import com.question.user.application.UserService;
import com.question.user.domain.User;
import com.question.user.io.response.UserResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class UserApiTest {

    private MockMvc mockMvc;
    private JwtTokenProvider provider;

    @Mock
    private UserService userService;

    @InjectMocks
    private UserApi userApi;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders.standaloneSetup(userApi).build();
    }

    @DisplayName("GET /api/users")
    @Test
    void findMe() throws Exception {
        //given
        User user1 = new User("username", "email@email.com", "password!!", "https://profiles/image.png");

        // when and then
        var perform = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/users")
                        .header(HttpHeaders.AUTHORIZATION, "Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiI4NjI2ZmYwNC01OWJjLTQ5ODItYjA3Yy04ZjQxZGJiMWE0ODgiLCJpYXQiOjE2ODA2NjAyMjYsImV4cCI6MTY4MDY5NjIyNn0.9cPEd_VpYBKu1_jE0FfGgnTlafeP99Xusr9UXrx243M")
        );
        perform.andExpect(status().isOk());
    }
}
