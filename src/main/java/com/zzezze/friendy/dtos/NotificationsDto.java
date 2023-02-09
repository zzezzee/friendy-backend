package com.zzezze.friendy.dtos;

import java.util.List;

public class NotificationsDto {
    List<PhotoCommentNotificationDto> photoCommentNotifications;

    public NotificationsDto(List<PhotoCommentNotificationDto> photoCommentNotifications) {
        this.photoCommentNotifications = photoCommentNotifications;
    }

    public List<PhotoCommentNotificationDto> getPhotoCommentNotifications() {
        return photoCommentNotifications;
    }
}
