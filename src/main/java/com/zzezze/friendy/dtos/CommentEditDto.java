package com.zzezze.friendy.dtos;

public class CommentEditDto {
    private String content;
    private Long id;

    public CommentEditDto() {
    }

    public CommentEditDto(String content, Long id) {
        this.content = content;
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public Long getId() {
        return id;
    }
}

