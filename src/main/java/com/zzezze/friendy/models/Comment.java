package com.zzezze.friendy.models;

import com.zzezze.friendy.dtos.CommentDto;
import com.zzezze.friendy.models.value_objects.Content;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.value_objects.ParentId;
import com.zzezze.friendy.models.value_objects.PhotoId;
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

    @Embedded
    private ParentId parentId;

    @Embedded
    private PhotoId photoId;

    @Embedded
    private Username username;

    @Embedded
    private Content content;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Comment() {
    }

    public Comment(Long id, ParentId parentId, PhotoId photoId, Username username, Content content, LocalDateTime createdAt) {
        this.id = id;
        this.parentId = parentId;
        this.photoId = photoId;
        this.username = username;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Comment(Long id, ParentId parentId, PhotoId photoId, Username username, Content content) {
        this.id = id;
        this.parentId = parentId;
        this.photoId = photoId;
        this.username = username;
        this.content = content;
    }

    public Comment(PhotoId photoId, Username username, Content content) {
        this.photoId = photoId;
        this.username = username;
        this.content = content;
    }

    public static Comment of(PhotoId photoId, Username username, Content content) {
        return new Comment(photoId, username, content);
    }

    public Long getId() {
        return id;
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
                new ParentId(1L),
                new PhotoId(1L),
                new Username("test"),
                new Content("댓글은 이렇게 달자")
        );
    }
}
