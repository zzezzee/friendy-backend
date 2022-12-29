package com.zzezze.friendy.models;

import com.zzezze.friendy.dtos.MiniHomepageDto;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class MiniHomepage {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Username username;

    @Embedded
    private Nickname nickname;

    @Embedded
    private ProfileImage profileImage;

    @Embedded
    private Introduction introduction;

    public MiniHomepage() {
    }

    public MiniHomepage(Long id, Username username, Nickname nickname, ProfileImage profileImage, Introduction introduction) {
        this.id = id;
        this.username = username;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.introduction = introduction;
    }

    public static MiniHomepage fake() {
        return new MiniHomepage(1L,
                new Username("test"),
                new Nickname("zzezze"),
                new ProfileImage("image_address"),
                new Introduction("미니홈피 소개"));
    }

    public MiniHomepageDto toDto() {
        return new MiniHomepageDto(
                nickname.getValue(),
                profileImage.getValue(),
                introduction.getValue()
        );
    }

    public void change(ProfileImage profileImage, Introduction introduction) {
        this.profileImage = profileImage;
        this.introduction = introduction;
    }
}
