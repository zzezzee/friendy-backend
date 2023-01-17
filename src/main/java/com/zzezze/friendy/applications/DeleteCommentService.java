package com.zzezze.friendy.applications;

import com.zzezze.friendy.exceptions.CommentNotFound;
import com.zzezze.friendy.exceptions.DeleteCommentFailed;
import com.zzezze.friendy.models.Comment;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DeleteCommentService {
    private final CommentRepository commentRepository;

    public DeleteCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Long delete(Username username, Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(CommentNotFound::new);

        if(!comment.getUsername().equals(username)){
            throw new DeleteCommentFailed();
        }

        commentRepository.delete(comment);

        return comment.getId();
    }
}
