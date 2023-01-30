package com.zzezze.friendy.dtos;

import com.zzezze.friendy.models.User;

import java.util.List;

public class UserExploreDto {
    private Long id;
    private String nickname;
    private String profileImage;
    private String introduction;

    private List<User> friendsTogether;

    public UserExploreDto() {
    }

    public UserExploreDto(Long id, String nickname, String profileImage, String introduction, List<User> friendsTogether) {
        this.id = id;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.introduction = introduction;
        this.friendsTogether = friendsTogether;
    }

    public Long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public String getIntroduction() {
        return introduction;
    }

    public List<User> getFriendsTogether() {
        return friendsTogether;
    }
}
