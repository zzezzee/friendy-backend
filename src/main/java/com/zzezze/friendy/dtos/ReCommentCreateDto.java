package com.zzezze.friendy.dtos;

public class ReCommentCreateDto {
    private Long postId;
    private String postType;
    private String content;
    private Long parentId;

    public ReCommentCreateDto() {
    }

    public ReCommentCreateDto(Long postId, String postType, String content, Long parentId) {
        this.postId = postId;
        this.postType = postType;
        this.content = content;
        this.parentId = parentId;
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

    public Long getParentId() {
        return parentId;
    }
}

