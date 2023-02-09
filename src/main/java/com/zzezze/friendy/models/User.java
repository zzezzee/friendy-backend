package com.zzezze.friendy.models;

import com.zzezze.friendy.dtos.UserDto;
import com.zzezze.friendy.dtos.UserExploreDto;
import com.zzezze.friendy.models.value_objects.Introduction;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.value_objects.Password;
import com.zzezze.friendy.models.value_objects.ProfileImage;
import com.zzezze.friendy.models.value_objects.Username;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Username username;

    @Embedded
    private Password encodedPassword;

    @Embedded
    private Nickname nickname;

    @Embedded
    private ProfileImage profileImage;

    @Embedded
    private Introduction introduction;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public User() {
    }

    public User(Long id, Username username, Password encodedPassword, Nickname nickname, ProfileImage profileImage, Introduction introduction) {
        this.id = id;
        this.username = username;
        this.encodedPassword = encodedPassword;
        this.nickname = nickname;
        this.profileImage = profileImage;
        this.introduction = introduction;
    }

    public Long getId() {
        return id;
    }

    public Username getUsername() {
        return username;
    }

    public Nickname getNickname() {
        return nickname;
    }

    public ProfileImage getProfileImage() {
        return profileImage;
    }

    public void authenticate(Password password) {
        this.encodedPassword.match(password);
    }

    public void changePassword(Password password) {
        encodedPassword = password.encode();
    }

    public void changeProfile(ProfileImage profileImage, Introduction introduction) {
        this.profileImage = profileImage;
        this.introduction = introduction;
    }

    public static User fake() {
        return new User(1L,
                new Username("test"),
                new Password("Password123!"),
                new Nickname("zzezze"),
                new ProfileImage("image_address"),
                new Introduction("미니홈피 소개")
        );
    }

    public static User fake(Username username) {
        return new User(1L,
                username,
                new Password("Password123!"),
                new Nickname("zzezze"),
                new ProfileImage("image_address"),
                new Introduction("미니홈피 소개")
        );
    }

    public UserDto toDto() {
        return new UserDto(
                id,
                nickname.getValue(),
                profileImage.getValue(),
                introduction.getValue()
        );
    }

    public UserExploreDto toExploreDto(List<User> userFriendsTogether) {
        return new UserExploreDto(
                id,
                nickname.getValue(),
                profileImage.getValue(),
                introduction.getValue(),
                userFriendsTogether
        );
    }
}
