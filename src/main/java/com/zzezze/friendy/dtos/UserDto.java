package com.zzezze.friendy.dtos;

public class UserDto {
    private String nickname;

    public UserDto() {
    }

    public UserDto(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }
}
