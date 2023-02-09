package com.zzezze.friendy.repositories.notifications;

import com.zzezze.friendy.dtos.PhotoCommentNotificationDto;
import com.zzezze.friendy.models.notifications.Notification;
import com.zzezze.friendy.models.notifications.PhotoCommentNotification;
import com.zzezze.friendy.models.value_objects.CommentId;
import com.zzezze.friendy.models.value_objects.PhotoId;
import com.zzezze.friendy.models.value_objects.Username;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface PhotoCommentNotificationRepository extends JpaRepository<PhotoCommentNotification, Long> {
    Optional<PhotoCommentNotification> findByCommentId(CommentId commentId);

    Optional<PhotoCommentNotification> findByPhotoId(PhotoId photoId);

    boolean existsByCommentId(CommentId commentId);

    boolean existsByPhotoId(PhotoId photoId);
}
