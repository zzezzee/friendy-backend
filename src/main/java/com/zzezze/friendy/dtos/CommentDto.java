package com.zzezze.friendy.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zzezze.friendy.models.Comment;

import java.time.LocalDateTime;
import java.util.List;

public class CommentDto {
    private Long id;
    private String profileImage;
    private String nickname;
    private String content;

    private List<ReCommentDto> reComments;

    private LocalDateTime createdAt;

    public CommentDto() {
    }

    public CommentDto(Long id, String profileImage, String nickname, String content, List<ReCommentDto> reComments, LocalDateTime createdAt) {
        this.id = id;
        this.profileImage = profileImage;
        this.nickname = nickname;
        this.content = content;
        this.reComments = reComments;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getNickname() {
        return nickname;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public List<ReCommentDto> getReComments() {
        return reComments;
    }

    public static CommentDto fake() {
        return new CommentDto(1L, "image_address", "nickname", "content", null, LocalDateTime.now());
    }

}
