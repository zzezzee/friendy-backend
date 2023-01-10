package com.zzezze.friendy.dtos;

public class UserDto {
    private Long id;
    private String nickname;
    private String profileImage;
    private String introduction;

    public UserDto() {
    }

    public UserDto(Long id, String nickname, String profileImage, String introduction) {
        this.id = id;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.introduction = introduction;
    }

    public Long getId() {
        return id;
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
