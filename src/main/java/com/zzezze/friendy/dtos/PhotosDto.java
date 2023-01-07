package com.zzezze.friendy.dtos;

import com.zzezze.friendy.models.Photo;

import java.util.List;

public class PhotosDto {
    List<PhotoDto> photos;

    public PhotosDto(List<PhotoDto> photos) {
        this.photos = photos;
    }

    public List<PhotoDto> getPhotos() {
        return photos;
    }
}
