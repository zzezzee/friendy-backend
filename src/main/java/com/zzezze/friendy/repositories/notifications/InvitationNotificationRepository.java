package com.zzezze.friendy.repositories.notifications;

import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.notifications.InvitationNotification;
import com.zzezze.friendy.models.notifications.LikeNotification;
import com.zzezze.friendy.models.value_objects.PhotoId;
import com.zzezze.friendy.models.value_objects.Type;
import com.zzezze.friendy.models.value_objects.Username;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InvitationNotificationRepository extends JpaRepository<InvitationNotification, Long> {
    boolean existsBySenderAndReceiverAndType(Username sender, Username receiver, Type invitation);

    Optional<InvitationNotification> findBySenderAndReceiverAndType(Username sender, Username receiver, Type invitation);
}
