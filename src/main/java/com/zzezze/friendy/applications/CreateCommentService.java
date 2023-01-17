package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.CommentDto;
import com.zzezze.friendy.models.Comment;
import com.zzezze.friendy.models.value_objects.Content;
import com.zzezze.friendy.models.value_objects.PhotoId;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.CommentRepository;
import com.zzezze.friendy.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CreateCommentService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public CreateCommentService(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public Long create(Username username, PhotoId photoId, Content content) {
        Comment comment = Comment.of(photoId, username, content);

        commentRepository.save(comment);

        return comment.getId();
    }
}
