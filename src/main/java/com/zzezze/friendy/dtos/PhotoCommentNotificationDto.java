package com.zzezze.friendy.dtos;

import java.time.LocalDateTime;

public class PhotoCommentNotificationDto {
    private Long id;
    private boolean checked;
    private LocalDateTime createdAt;
    private Long photoId;
    private String nickname;
    private String image;
    private String content;
    private String type;

    public PhotoCommentNotificationDto() {
    }

    public PhotoCommentNotificationDto(Long id, boolean checked, LocalDateTime createdAt, Long photoId, String nickname, String image, String content, String type) {
        this.id = id;
        this.checked = checked;
        this.createdAt = createdAt;
        this.photoId = photoId;
        this.nickname = nickname;
        this.image = image;
        this.content = content;
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

    public String getImage() {
        return image;
    }

    public String getContent() {
        return content;
    }

    public Long getPhotoId() {
        return photoId;
    }

    public String getType() {
        return type;
    }
}
