package com.zzezze.friendy.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class ChatRoomDto {
    private Long id;

    private String profileImage;
    private String nickname;

    private String recentChat;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime recentChatCreatedAt;

    public ChatRoomDto() {
    }

    public ChatRoomDto(Long id, String profileImage, String nickname, String recentChat, LocalDateTime recentChatCreatedAt) {
        this.id = id;
        this.profileImage = profileImage;
        this.nickname = nickname;
        this.recentChat = recentChat;
        this.recentChatCreatedAt = recentChatCreatedAt;
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

    public String getRecentChat() {
        return recentChat;
    }

    public LocalDateTime getRecentChatCreatedAt() {
        return recentChatCreatedAt;
    }
}
