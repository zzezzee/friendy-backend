package com.zzezze.friendy.dtos;

public class ChatRequestDto {
    private Long chatRoomId;
    private String nickname;
    private String content;

    public ChatRequestDto() {
    }

    public ChatRequestDto(Long chatRoomId, String nickname, String content) {
        this.chatRoomId = chatRoomId;
        this.nickname = nickname;
        this.content = content;
    }

    public Long getChatRoomId() {
        return chatRoomId;
    }

    public String getNickname() {
        return nickname;
    }

    public String getContent() {
        return content;
    }
}
