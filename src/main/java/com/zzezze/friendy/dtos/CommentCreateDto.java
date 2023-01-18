package com.zzezze.friendy.dtos;

public class CommentCreateDto {
    private Long postId;
    private String postType;
    private String content;

    public CommentCreateDto() {
    }

    public CommentCreateDto(Long postId, String postType, String content) {
        this.postId = postId;
        this.postType = postType;
        this.content = content;
    }

    public Long getPostId() {
        return postId;
    }

    public String getPostType() {
        return postType;
    }

    public String getContent() {
        return content;
    }
}

