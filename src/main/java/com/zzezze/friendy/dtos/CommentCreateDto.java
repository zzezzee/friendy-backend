package com.zzezze.friendy.dtos;

public class CommentCreateDto {
    private Long postId;
    private String postType;
    private String content;
    private String miniHomepageOwner;

    public CommentCreateDto() {
    }

    public CommentCreateDto(Long postId, String postType, String content, String miniHomepageOwner) {
        this.postId = postId;
        this.postType = postType;
        this.content = content;
        this.miniHomepageOwner = miniHomepageOwner;
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

    public String getMiniHomepageOwner() {
        return miniHomepageOwner;
    }
}

