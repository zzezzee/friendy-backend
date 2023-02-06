package com.zzezze.friendy.dtos;

import com.zzezze.friendy.models.Like;
import com.zzezze.friendy.models.Photo;

import java.util.List;

public class PhotoDetailDto {
    private PhotoDto photo;
    private List<LikeDto> likes;

    public PhotoDetailDto() {
    }

    public PhotoDetailDto(PhotoDto photo, List<LikeDto> likes) {
        this.photo = photo;
        this.likes = likes;
    }

    public PhotoDto getPhoto() {
        return photo;
    }

    public List<LikeDto> getLikes() {
        return likes;
    }

    public static PhotoDetailDto fake() {
        return new PhotoDetailDto(Photo.fake().toDto(), List.of(Like.fake().toDto()));
    }
}
