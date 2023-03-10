package com.zzezze.friendy.repositories.notifications;

import com.zzezze.friendy.models.notifications.InvitationNotification;
import com.zzezze.friendy.models.notifications.LikeNotification;
import com.zzezze.friendy.models.notifications.Notification;
import com.zzezze.friendy.models.notifications.PhotoCommentNotification;
import com.zzezze.friendy.models.value_objects.Type;
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
                    "WHERE (notification.receiver = :receiver AND notification.type = :type)")
    List<PhotoCommentNotification> findAllPhotoCommentNotificationByUsernameAndType(@Param("receiver") Username username,
                                                                                    @Param("type") Type type);

    @Query(
            "SELECT notification " +
                    "FROM Notification notification" +
                    "   LEFT JOIN LikeNotification likeNotification " +
                    "   ON notification.id = likeNotification.id " +
                    "WHERE (notification.receiver = :receiver AND notification.type = :type)")
    List<LikeNotification> findAllLikeNotificationByUsernameAndType(@Param("receiver") Username username,
                                                                    @Param("type") Type type);

    @Query(
            "SELECT notification " +
                    "FROM Notification notification" +
                    "   LEFT JOIN InvitationNotification invitationNotification " +
                    "   ON notification.id = invitationNotification.id " +
                    "WHERE (notification.receiver = :receiver AND notification.type = :type1) " +
                    "   OR (notification.receiver = :receiver AND notification.type = :type2)")
    List<InvitationNotification> findAllInvitationNotificationsByUsernameAndType(@Param("receiver") Username username,
                                                                                 @Param("type1") Type type1,
                                                                                 @Param("type2") Type type2);


    void deleteAllByReceiver(Username username);

    List<Notification> findAllByReceiver(Username username);

    void deleteAllByReceiverAndChecked(Username username, boolean b);
}
