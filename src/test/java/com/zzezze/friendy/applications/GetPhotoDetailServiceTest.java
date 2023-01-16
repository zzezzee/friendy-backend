package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.PhotoDetailDto;
import com.zzezze.friendy.models.Photo;
import com.zzezze.friendy.repositories.PhotoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class GetPhotoDetailServiceTest {
    PhotoRepository photoRepository;
    GetPhotoDetailService getPhotoDetailService;

    @BeforeEach
    void setup() {
        photoRepository = mock(PhotoRepository.class);
        getPhotoDetailService = new GetPhotoDetailService(photoRepository);
    }

    @Test
    void detail() {
        given(photoRepository.findById(1L))
                .willReturn(Optional.of(Photo.fake()));

        PhotoDetailDto photoDetailDto = getPhotoDetailService.detail(1L);

        verify(photoRepository).findById(1L);

        assertThat(photoDetailDto.getPhoto().getId()).isEqualTo(1L);
    }
}
