package com.zzezze.friendy.models.notifications;

import com.zzezze.friendy.applications.dtos.InvitationNotificationDto;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.value_objects.ProfileImage;
import com.zzezze.friendy.models.value_objects.Type;
import com.zzezze.friendy.models.value_objects.Username;
import jakarta.persistence.Entity;

@Entity
public class InvitationNotification extends Notification {
    public InvitationNotification() {
    }

    public InvitationNotification(Username sender, Username receiver, Type type) {
        super(sender, receiver, type);
    }

    public InvitationNotificationDto toDto(Nickname nickname, ProfileImage profileImage) {
        return new InvitationNotificationDto(
                getId(),
                isChecked(),
                getCreatedAt(),
                nickname.getValue(),
                profileImage.getValue(),
                getType().getValue()
        );
    }
}
