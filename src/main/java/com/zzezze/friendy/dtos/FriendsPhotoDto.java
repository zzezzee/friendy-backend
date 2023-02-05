package com.zzezze.friendy.dtos;

public class FriendsPhotoDto {
    private PhotoDto photo;
    private String profileImage;
    private String nickname;

    public FriendsPhotoDto() {
    }

    public FriendsPhotoDto(PhotoDto photo, String profileImage, String nickname) {
        this.photo = photo;
        this.profileImage = profileImage;
        this.nickname = nickname;
    }

    public PhotoDto getPhoto() {
        return photo;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getNickname() {
        return nickname;
    }

    public static FriendsPhotoDto fake() {
        return new FriendsPhotoDto(

        );
    }
}
