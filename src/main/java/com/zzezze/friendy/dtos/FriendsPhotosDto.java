package com.zzezze.friendy.dtos;

import java.util.List;

public class FriendsPhotosDto {
    List<FriendsPhotoDto> friendsPhotos;

    public FriendsPhotosDto(List<FriendsPhotoDto> friendsPhotos) {
        this.friendsPhotos = friendsPhotos;
    }

    public List<FriendsPhotoDto> getFriendsPhotos() {
        return friendsPhotos;
    }
}
