package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.CommentsDto;
import com.zzezze.friendy.models.Comment;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.ParentId;
import com.zzezze.friendy.models.value_objects.PostId;
import com.zzezze.friendy.repositories.CommentRepository;
import com.zzezze.friendy.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetPhotoCommentsServiceTest {
    CommentRepository commentRepository;
    UserRepository userRepository;
    GetCommentsService getPhotoCommentsService;

    @BeforeEach
    void setup() {
        commentRepository = mock(CommentRepository.class);
        userRepository = mock(UserRepository.class);
        getPhotoCommentsService = new GetCommentsService(commentRepository, userRepository);
    }

    @Test
    void list() {
        PostId postId = new PostId(1L);

        given(commentRepository.findAllByPostIdAndParentId(postId, null))
                .willReturn(List.of(Comment.fake()));

        given(userRepository.findByUsername(any()))
                .willReturn(Optional.of(User.fake()));

        given(commentRepository.findAllByParentId(any()))
                .willReturn((List.of(Comment.fake())));

        CommentsDto commentsDto = getPhotoCommentsService.list(postId);

        assertThat(commentsDto.getComments()).hasSize(1);
    }
}
