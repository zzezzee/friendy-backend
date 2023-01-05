package com.zzezze.friendy.dtos;

public class GuestBookDto {
    private Long id;
    private String username;
    private String writer;
    private String content;
    private String profileImage;

    public GuestBookDto() {
    }

    public GuestBookDto(Long id, String username, String writer, String content, String profileImage) {
        this.id = id;
        this.username = username;
        this.writer = writer;
        this.content = content;
        this.profileImage = profileImage;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getWriter() {
        return writer;
    }

    public String getContent() {
        return content;
    }

    public String getProfileImage() {
        return profileImage;
    }
}
