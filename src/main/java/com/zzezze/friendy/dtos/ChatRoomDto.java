package com.zzezze.friendy.dtos;

public class ChatRoomDto {
    private Long id;

    private String profileImage;
    private String nickname;

    public ChatRoomDto() {
    }

    public ChatRoomDto(Long id, String profileImage, String nickname) {
        this.id = id;
        this.profileImage = profileImage;
        this.nickname = nickname;
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
}
