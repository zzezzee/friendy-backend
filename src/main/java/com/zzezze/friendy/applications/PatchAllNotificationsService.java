package com.zzezze.friendy.applications;

import com.zzezze.friendy.models.notifications.Notification;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.notifications.NotificationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class PatchAllNotificationsService {
    private final NotificationRepository notificationRepository;

    public PatchAllNotificationsService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void checkAll(Username username) {
        List<Notification> notifications = notificationRepository.findAllByReceiver(username);

        notifications.forEach(Notification::check);
    }
}
