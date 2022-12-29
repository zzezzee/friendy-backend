package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.PhotosDto;
import com.zzezze.friendy.models.Nickname;
import com.zzezze.friendy.models.Photo;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.repositories.PhotoRepository;
import com.zzezze.friendy.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetPhotosServiceTest {
    PhotoRepository photoRepository;
    UserRepository userRepository;
    GetPhotosService getPhotosService;

    @BeforeEach
    void setup() {
        photoRepository = mock(PhotoRepository.class);
        userRepository = mock(UserRepository.class);
        getPhotosService = new GetPhotosService(userRepository, photoRepository);
    }

    @Test
    void list() {
        Nickname nickname = new Nickname("zzezze");
        User user = User.fake();

        given(userRepository.findByNickname(nickname))
                .willReturn(Optional.of(user));
        given(photoRepository.findAllByUsername(user.getUsername()))
                .willReturn(List.of(Photo.fake()));

        PhotosDto photosDto = getPhotosService.list(nickname);

        assertThat(photosDto.getPhotos().size()).isEqualTo(1);
    }
}
