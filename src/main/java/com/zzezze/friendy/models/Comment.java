package com.zzezze.friendy.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zzezze.friendy.dtos.CommentDto;
import com.zzezze.friendy.models.value_objects.Content;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.value_objects.ProfileImage;
import com.zzezze.friendy.models.value_objects.Username;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    private Long parent;

    @Embedded
    private Username username;

    @Embedded
    private Content content;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Comment() {
    }

    public Comment(Long id, Long parent, Username username, Content content) {
        this.id = id;
        this.parent = parent;
        this.username = username;
        this.content = content;
    }

    public Comment(Long id, Long parent, Username username, Content content, LocalDateTime createdAt) {
        this.id = id;
        this.parent = parent;
        this.username = username;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Username getUsername() {
        return username;
    }

    public CommentDto toDto(ProfileImage profileImage, Nickname nickname) {
        return new CommentDto(
                id,
                profileImage.getValue(),
                nickname.getValue(),
                content.getValue(),
                createdAt
        );
    }

    public static Comment fake() {
        return new Comment(
                1L,
                1L,
                new Username("test"),
                new Content("댓글은 이렇게 달자")
        );
    }
}
