package com.zzezze.friendy.applications;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zzezze.friendy.applications.notifications.PhotoCommentNotificationService;
import com.zzezze.friendy.exceptions.PhotoNotFound;
import com.zzezze.friendy.exceptions.UserNotFound;
import com.zzezze.friendy.models.Comment;
import com.zzezze.friendy.models.Photo;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Content;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.value_objects.PostId;
import com.zzezze.friendy.models.value_objects.PostType;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.CommentRepository;
import com.zzezze.friendy.repositories.PhotoRepository;
import com.zzezze.friendy.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CreateCommentService {
    private final CommentRepository commentRepository;
    private final PhotoCommentNotificationService notificationService;
    private final PhotoRepository photoRepository;
    private final UserRepository userRepository;

    public CreateCommentService(CommentRepository commentRepository, PhotoCommentNotificationService notificationService, PhotoRepository photoRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.notificationService = notificationService;
        this.photoRepository = photoRepository;
        this.userRepository = userRepository;
    }

    public Long create(Username username, PostId postId, PostType postType, Content content, Nickname nickname) throws JsonProcessingException {
        Comment comment = Comment.of(postId, postType, username, content);

        Comment savedComment = commentRepository.save(comment);

        if (postType.getValue().equals("photo")) {
            Photo photo = photoRepository.findById(postId.getValue())
                    .orElseThrow(PhotoNotFound::new);

            User user = userRepository.findByNickname(nickname)
                    .orElseThrow(UserNotFound::new);

            notificationService.sendNotification(username, user.getUsername(), photo, savedComment);
        }

        return comment.getId();
    }
}
