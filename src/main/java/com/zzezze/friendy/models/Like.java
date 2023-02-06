package com.zzezze.friendy.models;

import com.zzezze.friendy.dtos.LikeDto;
import com.zzezze.friendy.models.value_objects.PhotoId;
import com.zzezze.friendy.models.value_objects.Username;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "likes")
public class Like {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Username username;

    @Embedded
    private PhotoId photoId;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Like() {
    }

    public Like(Username username, PhotoId photoId) {
        this.username = username;
        this.photoId = photoId;
    }

    public LikeDto toDto() {
        return new LikeDto(id, username.getValue());
    }

    public static Like fake() {
        return new Like(
                new Username("test"),
                new PhotoId(1L)
        );
    }
}
