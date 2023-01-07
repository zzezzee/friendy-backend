package com.zzezze.friendy.dtos;

public class UserDto {
    private String nickname;
    private String profileImage;
    private String introduction;

    public UserDto() {
    }

    public UserDto(String nickname, String profileImage, String introduction) {
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.introduction = introduction;
    }

    public String getNickname() {
        return nickname;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getIntroduction() {
        return introduction;
    }
}
