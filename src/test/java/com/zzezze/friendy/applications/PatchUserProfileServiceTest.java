package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.UserDto;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Introduction;
import com.zzezze.friendy.models.value_objects.ProfileImage;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.parameters.P;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class PatchUserProfileServiceTest {
    UserRepository userRepository;
    PatchUserProfileService patchUserProfileService;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        patchUserProfileService = new PatchUserProfileService(userRepository);
    }

    @Test
    void patch() {
        Username username = new Username("test");
        ProfileImage profileImage = new ProfileImage("image_address");
        Introduction introduction = new Introduction("미니홈피 소개");


        given(userRepository.findByUsername(username))
                .willReturn(Optional.of(User.fake()));

        UserDto userDto = patchUserProfileService.patch(username, profileImage, introduction);

        assertThat(userDto.getNickname()).isEqualTo("zzezze");
    }
}
