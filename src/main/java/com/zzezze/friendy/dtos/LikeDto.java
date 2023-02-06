package com.zzezze.friendy.dtos;

public class LikeDto {
    private Long id;
    private String username;

    public LikeDto() {
    }

    public LikeDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
