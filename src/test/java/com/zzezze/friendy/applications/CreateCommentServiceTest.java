package com.zzezze.friendy.applications;

import com.zzezze.friendy.models.value_objects.Content;
import com.zzezze.friendy.models.value_objects.PostId;
import com.zzezze.friendy.models.value_objects.PostType;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateCommentServiceTest {
    CommentRepository commentRepository;
    CreateCommentService createCommentService;

    @BeforeEach
    void setup() {
        commentRepository = mock(CommentRepository.class);
        createCommentService = new CreateCommentService(commentRepository, notificationService, photoRepository, userRepository);
    }

    @Test
    void create() {
        Username username = new Username("test");
        PostId postId = new PostId(1L);
        PostType postType = new PostType("photo");
        Content content = new Content("댓글 내용");

        createCommentService.create(username, postId, postType, content, nickname);

        verify(commentRepository).save(any());
    }
}
