package com.zzezze.friendy.applications.dtos;

import java.time.LocalDateTime;

public class InvitationNotificationDto {
    private Long id;
    private boolean checked;
    private LocalDateTime createdAt;
    private String nickname;
    private String profileImage;
    private String type;

    public InvitationNotificationDto() {
    }

    public InvitationNotificationDto(Long id, boolean checked, LocalDateTime createdAt, String nickname, String profileImage, String type) {
        this.id = id;
        this.checked = checked;
        this.createdAt = createdAt;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public boolean isChecked() {
        return checked;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public String getNickname() {
        return nickname;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getType() {
        return type;
    }
}
