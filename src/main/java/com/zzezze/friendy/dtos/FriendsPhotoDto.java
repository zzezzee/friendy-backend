package com.zzezze.friendy.dtos;

public class FriendsPhotoDto {
    private PhotoDto photo;
    private String profileImage;
    private String nickname;
    private Long commentsCount;
    private Long likeCount;

    public FriendsPhotoDto() {
    }

    public FriendsPhotoDto(PhotoDto photo, String profileImage, String nickname, Long commentsCount, Long likeCount) {
        this.photo = photo;
        this.profileImage = profileImage;
        this.nickname = nickname;
        this.commentsCount = commentsCount;
        this.likeCount = likeCount;
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

    public Long getCommentsCount() {
        return commentsCount;
    }

    public Long getLikeCount() {
        return likeCount;
    }

    public static FriendsPhotoDto fake() {
        return new FriendsPhotoDto(

        );
    }
}
