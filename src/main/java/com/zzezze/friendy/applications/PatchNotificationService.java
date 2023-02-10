package com.zzezze.friendy.applications;

import com.zzezze.friendy.exceptions.CheckNotificationFailed;
import com.zzezze.friendy.exceptions.NotificationNotFound;
import com.zzezze.friendy.models.notifications.Notification;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.notifications.NotificationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PatchNotificationService {
    private NotificationRepository notificationRepository;

    public PatchNotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void check(Username username, Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(NotificationNotFound::new);

        if(!notification.getReceiver().equals(username)){
            throw new CheckNotificationFailed();
        }

        notification.check();
    }
}
