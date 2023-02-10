package com.zzezze.friendy.applications;

import com.zzezze.friendy.exceptions.DeleteNotificationFailed;
import com.zzezze.friendy.exceptions.NotificationNotFound;
import com.zzezze.friendy.models.notifications.Notification;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.notifications.NotificationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DeleteNotificationService {
    private final NotificationRepository notificationRepository;

    public DeleteNotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void delete(Username username, Long id) {
        Notification notification = notificationRepository.findById(id)
                .orElseThrow(NotificationNotFound::new);

        if(!notification.getReceiver().equals(username)){
            throw new DeleteNotificationFailed();
        }

        notificationRepository.delete(notification);
    }
}
