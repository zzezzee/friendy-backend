package com.zzezze.friendy.applications;

import com.zzezze.friendy.models.Comment;
import com.zzezze.friendy.models.value_objects.Content;
import com.zzezze.friendy.models.value_objects.PostId;
import com.zzezze.friendy.models.value_objects.PostType;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.CommentRepository;
import com.zzezze.friendy.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CreateCommentService {
    private final CommentRepository commentRepository;

    public CreateCommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    public Long create(Username username, PostId postId, PostType postType, Content content) {
        Comment comment = Comment.of(postId, postType, username, content);

        commentRepository.save(comment);

        return comment.getId();
    }
}
