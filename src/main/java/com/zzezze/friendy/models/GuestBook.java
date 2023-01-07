package com.zzezze.friendy.models;

import com.zzezze.friendy.dtos.GuestBookDto;
import com.zzezze.friendy.models.value_objects.Content;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.value_objects.ProfileImage;
import com.zzezze.friendy.models.value_objects.Username;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class GuestBook {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "owner"))
    private Username username;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "writer"))
    private Username writer;

    @Embedded
    private Content content;

    public GuestBook() {
    }

    public GuestBook(Long id, Username username, Username writer, Content content) {
        this.id = id;
        this.username = username;
        this.content = content;
        this.writer = writer;
    }

    public GuestBook(Username username, Username writer, Content content) {
        this.username = username;
        this.writer = writer;
        this.content = content;
    }

    public static GuestBook of(Username writer, Username username, Content content) {
        return new GuestBook(username, writer, content);
    }

    public Username getUsername() {
        return username;
    }

    public Username getWriter() {
        return writer;
    }

    public static GuestBook fake(Username username) {
        return new GuestBook(
                1L,
                username,
                new Username("test2"),
                new Content("내용")
        );
    }

    public static GuestBook fake() {
        return new GuestBook(
                1L,
                new Username("test"),
                new Username("test2"),
                new Content("내용")
        );
    }

    public GuestBookDto toDto(Nickname nickname, ProfileImage profileImage) {
        return new GuestBookDto(
                id,
                username.getValue(),
                writer.getValue(),
                content.getValue(),
                nickname.getValue(),
                profileImage.getValue()
        );
    }

    public void change(Content content) {
        this.content = content;
    }
}
