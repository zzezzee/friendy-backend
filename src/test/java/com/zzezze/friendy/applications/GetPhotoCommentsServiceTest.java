package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.CommentsDto;
import com.zzezze.friendy.models.Comment;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.PhotoId;
import com.zzezze.friendy.repositories.CommentRepository;
import com.zzezze.friendy.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetPhotoCommentsServiceTest {
    CommentRepository commentRepository;
    UserRepository userRepository;
    GetPhotoCommentsService getPhotoCommentsService;

    @BeforeEach
    void setup() {
        commentRepository = mock(CommentRepository.class);
        userRepository = mock(UserRepository.class);
        getPhotoCommentsService = new GetPhotoCommentsService(commentRepository, userRepository);
    }

    @Test
    void list() {
        PhotoId photoId = new PhotoId(1L);

        given(commentRepository.findAllByPhotoId(photoId))
                .willReturn(List.of(Comment.fake()));

        given(userRepository.findByUsername(any()))
                .willReturn(Optional.of(User.fake()));

        CommentsDto commentsDto = getPhotoCommentsService.list(photoId);

        assertThat(commentsDto.getComments()).hasSize(1);
    }
}
