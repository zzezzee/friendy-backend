package com.zzezze.friendy.dtos;

public class CurrentNicknameDto {
    private String currentNickname;

    public CurrentNicknameDto() {
    }

    public CurrentNicknameDto(String currentNickname) {
        this.currentNickname = currentNickname;
    }

    public String getCurrentNickname() {
        return currentNickname;
    }
}
