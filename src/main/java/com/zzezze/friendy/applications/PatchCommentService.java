package com.zzezze.friendy.applications;

import com.zzezze.friendy.exceptions.CommentNotFound;
import com.zzezze.friendy.exceptions.PatchCommentFailed;
import com.zzezze.friendy.models.Comment;
import com.zzezze.friendy.models.value_objects.Content;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PatchCommentService {
    private CommentRepository commentRepository;

    public PatchCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Long patch(Username username, Long id, Content content) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(CommentNotFound::new);

        if(!comment.getUsername().equals(username)){
            throw new PatchCommentFailed();
        }

        comment.changeContent(content);

        return comment.getId();
    }
}
