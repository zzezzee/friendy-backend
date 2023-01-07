package com.zzezze.friendy.applications;

import com.zzezze.friendy.models.Photo;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.PhotoRepository;
import com.zzezze.friendy.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeletePhotoServiceTest {
    PhotoRepository photoRepository;
    UserRepository userRepository;
    DeletePhotoService deletePhotoService;

    @BeforeEach
    void setup() {
        photoRepository = mock(PhotoRepository.class);
        userRepository = mock(UserRepository.class);
        deletePhotoService = new DeletePhotoService(photoRepository, userRepository);
    }

    @Test
    void delete() {
        Username username = new Username("test");

        given(userRepository.findByUsername(username))
                .willReturn(Optional.of(User.fake()));

        given(photoRepository.findById(1L))
                .willReturn(Optional.of(Photo.fake()));

        deletePhotoService.delete(username, 1L);

        verify(photoRepository).findById(1L);
        verify(photoRepository).deleteById(1L);
    }
}
