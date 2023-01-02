package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.PhotoDto;
import com.zzezze.friendy.models.Explanation;
import com.zzezze.friendy.models.Image;
import com.zzezze.friendy.models.Photo;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.Username;
import com.zzezze.friendy.repositories.PhotoRepository;
import com.zzezze.friendy.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class CreatePhotoServiceTest {
    CreatePhotoService createPhotoService;
    PhotoRepository photoRepository;
    UserRepository userRepository;

    @BeforeEach
    void setup() {
        photoRepository = mock(PhotoRepository.class);
        userRepository = mock(UserRepository.class);
        createPhotoService = new CreatePhotoService(photoRepository, userRepository);
    }

    @Test
    void create() {
        Username username = new Username("test");
        Image image = new Image("image_address");
        Explanation explanation = new Explanation("이미지 설명");

        given(photoRepository.save(any()))
                .willReturn(Photo.fake());

        given(userRepository.findByUsername(username))
                .willReturn(Optional.of(User.fake()));

        PhotoDto photoDto = createPhotoService.create(username, image, explanation);

        assertThat(photoDto.getExplanation()).isEqualTo("이미지 설명");
    }
}
