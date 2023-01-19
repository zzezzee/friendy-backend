package com.zzezze.friendy.applications;

import com.zzezze.friendy.models.value_objects.Content;
import com.zzezze.friendy.models.value_objects.ParentId;
import com.zzezze.friendy.models.value_objects.PostId;
import com.zzezze.friendy.models.value_objects.PostType;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateReCommentServiceTest {
    CommentRepository commentRepositrory;
    CreateReCommentService createReCommentService;

    @BeforeEach
    void setup() {
        commentRepositrory = mock(CommentRepository.class);
        createReCommentService = new CreateReCommentService(commentRepositrory);
    }

    @Test
    void create() {
        Username username = new Username("test");
        PostId postId = new PostId(1L);
        PostType postType = new PostType("photo");
        Content content = new Content("댓글 내용");
        ParentId parentId = new ParentId(1L);

        createReCommentService.create(username, postId, postType, content, parentId);
        
        verify(commentRepositrory).save(any());
    }
}
