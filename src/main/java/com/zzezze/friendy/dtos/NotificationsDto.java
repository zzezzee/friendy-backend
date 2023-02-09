package com.zzezze.friendy.dtos;

import java.util.List;

public class NotificationsDto {
    private List<PhotoCommentNotificationDto> photoCommentNotifications;
    private List<LikeNotificationDto> likeNotifications;

    public NotificationsDto(List<PhotoCommentNotificationDto> photoCommentNotifications, List<LikeNotificationDto> likeNotifications) {
        this.photoCommentNotifications = photoCommentNotifications;
        this.likeNotifications = likeNotifications;
    }

    public List<PhotoCommentNotificationDto> getPhotoCommentNotifications() {
        return photoCommentNotifications;
    }

    public List<LikeNotificationDto> getLikeNotifications() {
        return likeNotifications;
    }
}
