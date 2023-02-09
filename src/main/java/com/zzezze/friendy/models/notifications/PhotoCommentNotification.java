package com.zzezze.friendy.models.notifications;

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
public class PhotoCommentNotification extends Notification {
    @Embedded
    private PhotoId photoId;

    @Embedded
    private CommentId commentId;

    public PhotoCommentNotification() {
    }

    public PhotoCommentNotification(Username sender, Username receiver, Type type, PhotoId photoId, CommentId commentId) {
        super(sender, receiver, type);
        this.photoId = photoId;
        this.commentId = commentId;
    }

    public PhotoId getPhotoId() {
        return photoId;
    }

    public CommentId getCommentId() {
        return commentId;
    }

    public PhotoCommentNotificationDto toDto(Long photoId, Nickname nickname, Image image, Content content) {
        return new PhotoCommentNotificationDto(
                getId(),
                isChecked(),
                getCreatedAt(),
                photoId,
                nickname.getValue(),
                image.getValue(),
                content.getValue(),
                getType().getValue()
        );
    }
}
