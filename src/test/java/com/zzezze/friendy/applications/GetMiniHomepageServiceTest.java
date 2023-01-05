package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.MiniHomepageDto;
import com.zzezze.friendy.models.MiniHomepage;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.repositories.MiniHomepageRepository;
import com.zzezze.friendy.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetMiniHomepageServiceTest {
    MiniHomepageRepository miniHomepageRepository;
    UserRepository userRepository;
    GetMiniHomepageService getMiniHomepageService;

    @BeforeEach
    void setup() {
        miniHomepageRepository = mock(MiniHomepageRepository.class);
        userRepository = mock(UserRepository.class);
        getMiniHomepageService = new GetMiniHomepageService(miniHomepageRepository, userRepository);
    }

    @Test
    void miniHomepage() {
        Nickname nickname = new Nickname("zzezze");
        User user = User.fake();

        given(userRepository.findByNickname(nickname))
                .willReturn(Optional.of(user));

        given(miniHomepageRepository.findByUsername(user.getUsername()))
                .willReturn(Optional.of(MiniHomepage.fake()));

        MiniHomepageDto miniHomepageDto = getMiniHomepageService.miniHomepage(nickname);

        assertThat(miniHomepageDto.getProfileImage()).isEqualTo("image_address");
        assertThat(miniHomepageDto.getIntroduction()).isEqualTo("미니홈피 소개");
    }
}
