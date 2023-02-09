package com.zzezze.friendy.repositories;

import com.zzezze.friendy.models.notifications.Notification;
import com.zzezze.friendy.models.notifications.PhotoCommentNotification;
import com.zzezze.friendy.models.value_objects.Username;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
    @Query(
            "SELECT notification " +
                    "FROM Notification notification" +
                    "   LEFT JOIN PhotoCommentNotification photoCommentNotification " +
                    "   ON notification.id = photoCommentNotification.id " +
                    "WHERE (notification.receiver = :receiver)")
    List<PhotoCommentNotification> findAllPhotoCommentNotificationByUsername(@Param("receiver") Username username);
}
