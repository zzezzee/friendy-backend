package com.zzezze.friendy.dtos;

public class CommentCreateDto {
    private String content;
    private Long photoId;

    public CommentCreateDto() {
    }

    public CommentCreateDto(String content, Long photoId) {
        this.content = content;
        this.photoId = photoId;
    }

    public String getContent() {
        return content;
    }

    public Long getPhotoId() {
        return photoId;
    }
}

