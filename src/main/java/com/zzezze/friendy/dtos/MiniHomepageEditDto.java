package com.zzezze.friendy.dtos;

public class MiniHomepageEditDto {
    private String profileImage;

    private String introduction;

    public MiniHomepageEditDto() {
    }

    public MiniHomepageEditDto(String profileImage, String introduction) {
        this.profileImage = profileImage;
        this.introduction = introduction;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getIntroduction() {
        return introduction;
    }
}
