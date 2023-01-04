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
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PatchPhotoServiceTest {
    PhotoRepository photoRepository;
    UserRepository userRepository;
    PatchPhotoService patchPhotoService;

    @BeforeEach
    void setup() {
        photoRepository = mock(PhotoRepository.class);
        userRepository = mock(UserRepository.class);
        patchPhotoService = new PatchPhotoService(userRepository, photoRepository);
    }

    @Test
    void patch() {
        Username username = new Username("test");
        Long id = 1L;
        Image image = new Image("image_address");
        Explanation explanation = new Explanation("사진 설명");

        given(userRepository.findByUsername(username))
                .willReturn(Optional.of(User.fake()));

        Photo photo = Photo.fake();

        given(photoRepository.findById(id))
                .willReturn(Optional.of(photo));

        PhotoDto photoDto = patchPhotoService.patch(username, id, image, explanation);

        assertThat(photoDto).isNotNull();
    }
}
