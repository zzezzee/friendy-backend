package com.zzezze.friendy.dtos;

public class GuestBookRegistrationDto {
    private String nickname;
    private String content;

    public GuestBookRegistrationDto() {
    }

    public GuestBookRegistrationDto(String nickname, String content) {
        this.nickname = nickname;
        this.content = content;
    }

    public String getNickname() {
        return nickname;
    }

    public String getContent() {
        return content;
    }
}
