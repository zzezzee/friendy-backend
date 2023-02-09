package com.zzezze.friendy.repositories.notifications;

import com.zzezze.friendy.models.notifications.LikeNotification;
import com.zzezze.friendy.models.notifications.PhotoCommentNotification;
import com.zzezze.friendy.models.value_objects.CommentId;
import com.zzezze.friendy.models.value_objects.PhotoId;
import com.zzezze.friendy.models.value_objects.Username;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LikeNotificationRepository extends JpaRepository<LikeNotification, Long> {
    Optional<LikeNotification> findByPhotoId(PhotoId photoId);

    void deleteByPhotoId(PhotoId photoId);

    boolean existsByPhotoId(PhotoId photoId);
}
