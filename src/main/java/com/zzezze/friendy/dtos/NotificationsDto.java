package com.zzezze.friendy.dtos;

import com.zzezze.friendy.applications.dtos.InvitationNotificationDto;

import java.util.List;

public class NotificationsDto {
    private List<PhotoCommentNotificationDto> photoCommentNotifications;
    private List<LikeNotificationDto> likeNotifications;
    private List<InvitationNotificationDto> invitationNotifications;

    public NotificationsDto(List<PhotoCommentNotificationDto> photoCommentNotifications, List<LikeNotificationDto> likeNotifications, List<InvitationNotificationDto> invitationNotifications) {
        this.photoCommentNotifications = photoCommentNotifications;
        this.likeNotifications = likeNotifications;
        this.invitationNotifications = invitationNotifications;
    }

    public List<PhotoCommentNotificationDto> getPhotoCommentNotifications() {
        return photoCommentNotifications;
    }

    public List<LikeNotificationDto> getLikeNotifications() {
        return likeNotifications;
    }

    public List<InvitationNotificationDto> getInvitationNotifications() {
        return invitationNotifications;
    }
}
