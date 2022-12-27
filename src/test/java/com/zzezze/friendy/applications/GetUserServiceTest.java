package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.UserDto;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.Username;
import com.zzezze.friendy.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetUserServiceTest {
    private GetUserService getUserService;
    private UserRepository userRepository;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        getUserService = new GetUserService(userRepository);
    }

    @Test
    void detail() {
        Username username = new Username("zzezze");
        given(userRepository.findByUsername(username))
                .willReturn(Optional.of(User.fake()));

        UserDto userDto = getUserService.detail(username);

        assertThat(userDto.getNickname()).isEqualTo("zzezze");
    }
}
