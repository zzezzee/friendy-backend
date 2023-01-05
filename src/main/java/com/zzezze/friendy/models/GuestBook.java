package com.zzezze.friendy.models;

import com.zzezze.friendy.dtos.GuestBookDto;
import com.zzezze.friendy.models.value_objects.Content;
import com.zzezze.friendy.models.value_objects.ProfileImage;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.models.value_objects.Writer;
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
    private Username username;

    @Embedded
    private Writer writer;

    @Embedded
    private Content content;

    @Embedded
    private ProfileImage profileImage;

    public GuestBook() {
    }

    public GuestBook(Long id, Username username, Content content, Writer writer, ProfileImage profileImage) {
        this.id = id;
        this.username = username;
        this.content = content;
        this.writer = writer;
        this.profileImage = profileImage;
    }

    public Username getUsername() {
        return username;
    }

    public Writer getWriter() {
        return writer;
    }

    public static GuestBook fake(Username username) {
        return new GuestBook(
                1L,
                username,
                new Content("내용"),
                new Writer("writer"),
                new ProfileImage("image_address")
        );
    }

    public static GuestBook fake() {
        return new GuestBook(
                1L,
                new Username("test"),
                new Content("내용"),
                new Writer("writer"),
                new ProfileImage("image_address")
        );
    }

    public GuestBookDto toDto() {
        return new GuestBookDto(
                id,
                username.getValue(),
                writer.getValue(),
                content.getValue(),
                profileImage.getValue()
        );
    }
}
