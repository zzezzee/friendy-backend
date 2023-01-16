package com.zzezze.friendy.dtos;

import com.zzezze.friendy.models.Comment;
import com.zzezze.friendy.models.Photo;

import java.util.List;

public class PhotoDetailDto {
    private PhotoDto photo;
    private List<CommentDto> comments;

    public PhotoDetailDto(PhotoDto photo, List<CommentDto> commentDtos) {
        this.photo = photo;
        this.comments = commentDtos;
    }

    public PhotoDto getPhoto() {
        return photo;
    }

    public List<CommentDto> getComments() {
        return comments;
    }

    public static PhotoDetailDto fake() {
        return new PhotoDetailDto(Photo.fake().toDto(), List.of(CommentDto.fake()));
    }
}
