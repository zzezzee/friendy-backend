package com.zzezze.friendy.dtos;

import com.zzezze.friendy.models.Photo;

public class PhotoDetailDto {
    private PhotoDto photo;

    public PhotoDetailDto(PhotoDto photo) {
        this.photo = photo;
    }

    public PhotoDto getPhoto() {
        return photo;
    }

    public static PhotoDetailDto fake() {
        return new PhotoDetailDto(Photo.fake().toDto());
    }
}
