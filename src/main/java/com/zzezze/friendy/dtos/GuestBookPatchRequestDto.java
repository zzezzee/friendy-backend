package com.zzezze.friendy.dtos;

public class GuestBookPatchRequestDto {
    private Long id;
    private String content;

    public GuestBookPatchRequestDto() {
    }

    public GuestBookPatchRequestDto(Long id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public Long getId() {
        return id;
    }
}
