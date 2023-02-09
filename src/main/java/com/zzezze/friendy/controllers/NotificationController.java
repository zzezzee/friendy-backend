package com.zzezze.friendy.controllers;

import com.zzezze.friendy.applications.GetNotificationsService;
import com.zzezze.friendy.applications.notifications.PhotoCommentNotificationService;
import com.zzezze.friendy.dtos.NotificationsDto;
import com.zzezze.friendy.models.value_objects.Username;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class NotificationController {
    private final PhotoCommentNotificationService notificationService;
    private final GetNotificationsService getNotificationsService;

    public NotificationController(PhotoCommentNotificationService notificationService, GetNotificationsService getNotificationsService) {
        this.notificationService = notificationService;
        this.getNotificationsService = getNotificationsService;
    }

    @GetMapping("notifications")
    public NotificationsDto list(
            @RequestAttribute("username") Username username
    ) {
        return getNotificationsService.list(username);
    }

    @GetMapping(value = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter connect(
            @RequestAttribute("username") Username username
    ) {
        return notificationService.subscribe(username);
    }
}
