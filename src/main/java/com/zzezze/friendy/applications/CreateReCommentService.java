package com.zzezze.friendy.applications;

import com.zzezze.friendy.models.Comment;
import com.zzezze.friendy.models.value_objects.Content;
import com.zzezze.friendy.models.value_objects.ParentId;
import com.zzezze.friendy.models.value_objects.PostId;
import com.zzezze.friendy.models.value_objects.PostType;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.CommentRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CreateReCommentService {
    private final CommentRepository commentRepository;

    public CreateReCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Long create(Username username, PostId postId, PostType postType, Content content, ParentId parentId) {
        Comment comment = Comment.of(postId, postType, username, content, parentId);

        commentRepository.save(comment);

        return comment.getId();
    }
}
