package com.zzezze.friendy.controllers;

import com.zzezze.friendy.applications.DeleteAllNotificationsService;
import com.zzezze.friendy.applications.DeleteCheckedNotificationsService;
import com.zzezze.friendy.applications.DeleteNotificationService;
import com.zzezze.friendy.applications.GetNotificationsService;
import com.zzezze.friendy.applications.PatchAllNotificationsService;
import com.zzezze.friendy.applications.PatchNotificationService;
import com.zzezze.friendy.applications.notifications.NotificationService;
import com.zzezze.friendy.dtos.NotificationsDto;
import com.zzezze.friendy.models.value_objects.Username;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class NotificationController {
    private final GetNotificationsService getNotificationsService;
    private final NotificationService notificationService;
    private final DeleteAllNotificationsService deleteAllNotificationsService;
    private final PatchAllNotificationsService patchAllNotificationsService;
    private final DeleteNotificationService deleteNotificationService;
    private final PatchNotificationService patchNotificationService;
    private final DeleteCheckedNotificationsService deleteCheckedNotificationsService;

    public NotificationController(GetNotificationsService getNotificationsService, NotificationService notificationService, DeleteAllNotificationsService deleteAllNotificationsService, PatchAllNotificationsService patchAllNotificationsService, DeleteNotificationService deleteNotificationService, PatchNotificationService patchNotificationService, DeleteCheckedNotificationsService deleteCheckedNotificationsService) {
        this.getNotificationsService = getNotificationsService;
        this.notificationService = notificationService;
        this.deleteAllNotificationsService = deleteAllNotificationsService;
        this.patchAllNotificationsService = patchAllNotificationsService;
        this.deleteNotificationService = deleteNotificationService;
        this.patchNotificationService = patchNotificationService;
        this.deleteCheckedNotificationsService = deleteCheckedNotificationsService;
    }

    @GetMapping("notifications")
    public NotificationsDto list(
            @RequestAttribute("username") Username username
    ) {
        return getNotificationsService.list(username);
    }

    @PatchMapping("notifications")
    public void checkAll(
            @RequestAttribute("username") Username username
    ) {
        patchAllNotificationsService.checkAll(username);
    }

    @PatchMapping("notifications/{id}")
    public void check(
            @RequestAttribute("username") Username username,
            @PathVariable Long id
    ) {
        patchNotificationService.check(username, id);
    }

    @DeleteMapping("notifications")
    public void deleteAll(
            @RequestAttribute("username") Username username
    ) {
        deleteAllNotificationsService.deleteAll(username);
    }

    @DeleteMapping("notifications/checked")
    public void deleteChecked(
            @RequestAttribute("username") Username username
    ) {
        deleteCheckedNotificationsService.deleteChecked(username);
    }

    @DeleteMapping("notifications/{id}")
    public void delete(
            @RequestAttribute("username") Username username,
            @PathVariable Long id
    ) {
        deleteNotificationService.delete(username, id);
    }

    @GetMapping(value = "/connect", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public SseEmitter connect(
            @RequestAttribute("username") Username username
    ) {
        return notificationService.subscribe(username);
    }
}
