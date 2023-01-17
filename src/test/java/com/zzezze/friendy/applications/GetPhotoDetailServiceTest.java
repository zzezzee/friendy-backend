package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.PhotoDetailDto;
import com.zzezze.friendy.models.Comment;
import com.zzezze.friendy.models.Photo;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.repositories.CommentRepository;
import com.zzezze.friendy.repositories.PhotoRepository;
import com.zzezze.friendy.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class GetPhotoDetailServiceTest {
    PhotoRepository photoRepository;
    CommentRepository commentRepository;
    UserRepository userRepository;
    GetPhotoDetailService getPhotoDetailService;

    @BeforeEach
    void setup() {
        photoRepository = mock(PhotoRepository.class);
        commentRepository = mock(CommentRepository.class);
        userRepository = mock(UserRepository.class);
        getPhotoDetailService = new GetPhotoDetailService(photoRepository, commentRepository, userRepository);
    }

    @Test
    void detail() {
        given(photoRepository.findById(1L))
                .willReturn(Optional.of(Photo.fake()));

        given(commentRepository.findAllByPhotoId(any()))
                .willReturn(List.of(Comment.fake()));

        given(userRepository.findByUsername(any()))
                .willReturn(Optional.of(User.fake()));

        PhotoDetailDto photoDetailDto = getPhotoDetailService.detail(1L);

        assertThat(photoDetailDto.getPhoto().getId()).isEqualTo(1L);
        assertThat(photoDetailDto.getComments().get(0).getContent()).isEqualTo("댓글은 이렇게 달자");
    }
}
