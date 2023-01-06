package com.zzezze.friendy.dtos;

public class UserProfilePatchRequestDto {
    private String profileImage;

    private String introduction;

    public UserProfilePatchRequestDto() {
    }

    public UserProfilePatchRequestDto(String profileImage, String introduction) {
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
