package com.zzezze.friendy.models.notifications;

import com.zzezze.friendy.dtos.LikeNotificationDto;
import com.zzezze.friendy.dtos.PhotoCommentNotificationDto;
import com.zzezze.friendy.models.value_objects.CommentId;
import com.zzezze.friendy.models.value_objects.Content;
import com.zzezze.friendy.models.value_objects.Image;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.value_objects.PhotoId;
import com.zzezze.friendy.models.value_objects.Type;
import com.zzezze.friendy.models.value_objects.Username;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

@Entity
public class LikeNotification extends Notification {
    @Embedded
    private PhotoId photoId;

    public LikeNotification() {
    }

    public LikeNotification(Username sender, Username receiver, Type type, PhotoId photoId) {
        super(sender, receiver, type);
        this.photoId = photoId;
    }

    public PhotoId getPhotoId() {
        return photoId;
    }

    public LikeNotificationDto toDto(Long photoId, Nickname nickname, Image image) {
        return new LikeNotificationDto(
                getId(),
                isChecked(),
                getCreatedAt(),
                photoId,
                nickname.getValue(),
                image.getValue(),
                getType().getValue()
        );
    }
}
