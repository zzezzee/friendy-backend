package com.zzezze.friendy.applications;

import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.notifications.NotificationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DeleteCheckedNotificationsService {
    private final NotificationRepository notificationRepository;

    public DeleteCheckedNotificationsService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void deleteChecked(Username username) {
        notificationRepository.deleteAllByReceiverAndChecked(username, true);
    }
}
