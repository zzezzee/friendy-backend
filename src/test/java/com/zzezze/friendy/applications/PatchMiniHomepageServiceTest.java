package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.MiniHomepageDto;
import com.zzezze.friendy.models.Introduction;
import com.zzezze.friendy.models.MiniHomepage;
import com.zzezze.friendy.models.ProfileImage;
import com.zzezze.friendy.models.Username;
import com.zzezze.friendy.repositories.MiniHomepageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class PatchMiniHomepageServiceTest {
    PatchMiniHomepageService patchMiniHomepageService;
    MiniHomepageRepository miniHomepageRepository;

    @BeforeEach
    void setup() {
        miniHomepageRepository = mock(MiniHomepageRepository.class);
        patchMiniHomepageService = new PatchMiniHomepageService(miniHomepageRepository);
    }

    @Test
    void patch() {
        Username username = new Username("zzezze");
        ProfileImage profileImage = new ProfileImage("image_address2");
        Introduction introduction = new Introduction("introduction2");

        given(miniHomepageRepository.findByUsername(username))
                .willReturn(Optional.of(MiniHomepage.fake()));

        MiniHomepageDto miniHomepageDto = patchMiniHomepageService.patch(username, profileImage, introduction);

        assertThat(miniHomepageDto.getProfileImage()).isEqualTo("image_address2");
        assertThat(miniHomepageDto.getIntroduction()).isEqualTo("introduction2");
    }
}
