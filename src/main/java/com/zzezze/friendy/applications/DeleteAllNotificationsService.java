package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.NotificationsDto;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.notifications.NotificationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional

public class DeleteAllNotificationsService {
    private final NotificationRepository notificationRepository;

    public DeleteAllNotificationsService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void deleteAll(Username username) {
        notificationRepository.deleteAllByReceiver(username);
    }
}
