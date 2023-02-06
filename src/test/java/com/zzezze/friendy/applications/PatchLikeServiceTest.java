package com.zzezze.friendy.applications;

import com.zzezze.friendy.models.Like;
import com.zzezze.friendy.models.value_objects.PhotoId;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.LikeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.parameters.P;

import javax.sound.midi.Patch;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PatchLikeServiceTest {
    LikeRepository likeRepository;
    PatchLikeService patchLikeService;

    @BeforeEach
    void setup() {
        likeRepository = mock(LikeRepository.class);
        patchLikeService = new PatchLikeService(likeRepository);
    }

    @Test
    void cancelLike() {
        Username username = new Username("test");
        PhotoId photoId = new PhotoId(1L);

        given(likeRepository.existsByPhotoIdAndUsername(photoId, username))
                .willReturn(true);

        patchLikeService.patch(username, photoId);

        verify(likeRepository).deleteByPhotoIdAndUsername(photoId, username);
    }

    @Test
    void addLike() {
        Username username = new Username("test");
        PhotoId photoId = new PhotoId(1L);

        given(likeRepository.existsByPhotoIdAndUsername(photoId, username))
                .willReturn(false);

        patchLikeService.patch(username, photoId);

        verify(likeRepository).save(any());
    }
}

