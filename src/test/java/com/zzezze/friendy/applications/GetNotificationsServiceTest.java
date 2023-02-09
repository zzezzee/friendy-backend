package com.zzezze.friendy.applications;

import com.zzezze.friendy.repositories.CommentRepository;
import com.zzezze.friendy.repositories.notifications.NotificationRepository;
import com.zzezze.friendy.repositories.PhotoRepository;
import com.zzezze.friendy.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class GetNotificationsServiceTest {
    NotificationRepository notificationRepository;
    PhotoRepository photoRepository;
    CommentRepository commentRepository;
    UserRepository userRepository;
    GetNotificationsService getNotificationsService;

    @BeforeEach
    void setup() {
        notificationRepository = mock(NotificationRepository.class);
        photoRepository = mock(PhotoRepository.class);
        commentRepository = mock(CommentRepository.class);
        userRepository = mock(UserRepository.class);
        getNotificationsService = new GetNotificationsService(notificationRepository, photoRepository, commentRepository, userRepository);
    }

    @Test
    void list() {

    }
}
