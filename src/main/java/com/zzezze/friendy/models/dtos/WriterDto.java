package com.zzezze.friendy.models.dtos;

public class WriterDto {
    private String username;
    private String profileImage;

    public WriterDto() {
    }

    public WriterDto(String username, String profileImage) {
        this.username = username;
        this.profileImage = profileImage;
    }

    public String getUsername() {
        return username;
    }

    public String getProfileImage() {
        return profileImage;
    }
}
