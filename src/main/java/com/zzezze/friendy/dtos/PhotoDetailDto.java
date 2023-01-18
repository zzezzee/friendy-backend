package com.zzezze.friendy.dtos;

import com.zzezze.friendy.models.Comment;
import com.zzezze.friendy.models.Photo;

import java.util.List;

public class PhotoDetailDto {
    private PhotoDto photo;

    public PhotoDetailDto() {
    }

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
