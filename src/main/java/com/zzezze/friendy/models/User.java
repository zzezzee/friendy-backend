package com.zzezze.friendy.models;

import com.zzezze.friendy.dtos.UserDto;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

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

    public User() {
    }

    public User(Long id, Username username, Password encodedPassword, Nickname nickname) {
        this.id = id;
        this.username = username;
        this.encodedPassword = encodedPassword;
        this.nickname = nickname;
    }

    public Username getUsername() {
        return username;
    }

    public Nickname getNickname() {
        return nickname;
    }

    public void authenticate(Password password) {
        this.encodedPassword.match(password);
    }

    public void changePassword(Password password) {
        encodedPassword = password.encode();
    }

    public static User fake() {
        return new User(1L, new Username("test"), new Password("Password123!"), new Nickname("zzezze"));
    }

    public UserDto toDto() {
        return new UserDto(nickname.getValue());
    }
}
