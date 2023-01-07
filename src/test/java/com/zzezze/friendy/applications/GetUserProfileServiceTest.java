package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.UserDto;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.repositories.PhotoRepository;
import com.zzezze.friendy.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetUserProfileServiceTest {
    UserRepository userRepository;
    GetUserProfileService getUserprofileService;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        getUserprofileService = new GetUserProfileService(userRepository);
    }

    @Test
    void profile() {
        Nickname nickname = new Nickname("zzezze");

        given(userRepository.findByNickname(nickname))
                .willReturn(Optional.of(User.fake()));

        UserDto userDto = getUserprofileService.profile(nickname);

        assertThat(userDto.getIntroduction()).isEqualTo("미니홈피 소개");
    }
}
