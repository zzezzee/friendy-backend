package com.zzezze.friendy.applications;

import com.zzezze.friendy.exceptions.PatchCommentFailed;
import com.zzezze.friendy.models.Comment;
import com.zzezze.friendy.models.value_objects.Content;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.CommentRepository;
import net.bytebuddy.build.ToStringPlugin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class PatchCommentServiceTest {
    CommentRepository commentRepository;
    PatchCommentService patchCommentService;

    @BeforeEach
    void setup() {
        commentRepository = mock(CommentRepository.class);
        patchCommentService = new PatchCommentService(commentRepository);
    }

    @Test
    void patch() {
        Username username = new Username("test");
        Content content = new Content("수정할 내용");
        Comment comment = Comment.fake();

        given(commentRepository.findById(any()))
                .willReturn(Optional.of(comment));

        patchCommentService.patch(username, 1L, content);

        assertThat(comment.getContent()).isEqualTo(content);
    }

    @Test
    void patchCommentNotMine() {
        Username username = new Username("other");
        Content content = new Content("수정할 내용");
        Comment comment = Comment.fake();

        given(commentRepository.findById(any()))
                .willReturn(Optional.of(comment));

        assertThrows(PatchCommentFailed.class, () -> {
                patchCommentService.patch(username, 1L, content);
        });
    }
}
