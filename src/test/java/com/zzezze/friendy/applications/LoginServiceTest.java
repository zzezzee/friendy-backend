package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.LoginResultDto;
import com.zzezze.friendy.models.Password;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.Username;
import com.zzezze.friendy.repositories.UserRepository;
import com.zzezze.friendy.utils.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class LoginServiceTest {
    LoginService loginService;
    UserRepository userRepository;
    private JwtUtil jwtUtil;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        jwtUtil = new JwtUtil("SECRET");
        loginService = new LoginService(userRepository, jwtUtil);
    }

    @Test
    void loginSuccess() {
        Username username = new Username("username");
        Password password = new Password("Password123!");

        User user = User.fake();

        user.changePassword(password);

        given(userRepository.findByUsername(username))
                .willReturn(Optional.of(user));

        LoginResultDto loginResultDto = loginService.login(username, password);

        assertThat(loginResultDto.getNickname()).isEqualTo("zzezze");
    }
}
