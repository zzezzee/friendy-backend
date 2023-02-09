package com.zzezze.friendy.applications;

import com.zzezze.friendy.exceptions.CommentNotFound;
import com.zzezze.friendy.exceptions.DeleteCommentFailed;
import com.zzezze.friendy.exceptions.PhotoCommentNotificationNotFound;
import com.zzezze.friendy.models.Comment;
import com.zzezze.friendy.models.notifications.PhotoCommentNotification;
import com.zzezze.friendy.models.value_objects.CommentId;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.CommentRepository;
import com.zzezze.friendy.repositories.notifications.NotificationRepository;
import com.zzezze.friendy.repositories.notifications.PhotoCommentNotificationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DeleteCommentService {
    private final CommentRepository commentRepository;
    private final NotificationRepository notificationRepository;
    private final PhotoCommentNotificationRepository photoCommentNotificationRepository;

    public DeleteCommentService(CommentRepository commentRepository, NotificationRepository notificationRepository, PhotoCommentNotificationRepository photoCommentNotificationRepository) {
        this.commentRepository = commentRepository;
        this.notificationRepository = notificationRepository;
        this.photoCommentNotificationRepository = photoCommentNotificationRepository;
    }

    public Long delete(Username username, Long id) {
        Comment comment = commentRepository.findById(id)
                .orElseThrow(CommentNotFound::new);

        if (!comment.getUsername().equals(username)) {
            throw new DeleteCommentFailed();
        }

        if (photoCommentNotificationRepository.existsByCommentId(new CommentId(comment.getId()))) {
            PhotoCommentNotification photoCommentNotification =
                    photoCommentNotificationRepository.findByCommentId(new CommentId(comment.getId()))
                            .orElseThrow(PhotoCommentNotificationNotFound::new);

            notificationRepository.deleteById(photoCommentNotification.getId());
            photoCommentNotificationRepository.delete(photoCommentNotification);
        }

        commentRepository.delete(comment);

        return comment.getId();
    }
}
