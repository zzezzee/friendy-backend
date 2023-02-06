package com.zzezze.friendy.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ReCommentDto {
    private Long id;
    private String profileImage;
    private String nickname;
    private String content;

    private LocalDateTime createdAt;

    public ReCommentDto() {
    }

    public ReCommentDto(Long id, String profileImage, String nickname, String content, LocalDateTime createdAt) {
        this.id = id;
        this.profileImage = profileImage;
        this.nickname = nickname;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getNickname() {
        return nickname;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
