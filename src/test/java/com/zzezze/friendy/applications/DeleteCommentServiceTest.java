package com.zzezze.friendy.applications;

import com.zzezze.friendy.exceptions.DeleteCommentFailed;
import com.zzezze.friendy.models.Comment;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeleteCommentServiceTest {
    DeleteCommentService deleteCommentService;
    CommentRepository commentRepository;

    @BeforeEach
    void setup() {
        commentRepository = mock(CommentRepository.class);
        deleteCommentService = new DeleteCommentService(commentRepository, notificationRepository, photoCommentNotificationRepository);
    }

    @Test
    void delete() {
        Username username = new Username("test");
        Comment comment = Comment.fake();

        given(commentRepository.findById(any()))
                .willReturn(Optional.of(comment));

        deleteCommentService.delete(username, 1L);

        verify(commentRepository).delete(comment);
    }

    @Test
    void deleteNotMine() {
        Username username = new Username("test1");
        Comment comment = Comment.fake();

        given(commentRepository.findById(any()))
                .willReturn(Optional.of(comment));


        assertThrows(DeleteCommentFailed.class, () -> {
            deleteCommentService.delete(username, 1L);
        });
    }
}
