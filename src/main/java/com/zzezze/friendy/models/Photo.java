package com.zzezze.friendy.models;

import com.zzezze.friendy.dtos.PhotoDto;
import com.zzezze.friendy.models.value_objects.Explanation;
import com.zzezze.friendy.models.value_objects.Image;
import com.zzezze.friendy.models.value_objects.Username;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class Photo {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private Username username;

    @Embedded
    private Image image;

    @Embedded
    private Explanation explanation;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Photo() {
    }

    public Photo(Long id, Username username, Image image, Explanation explanation) {
        this.id = id;
        this.username = username;
        this.image = image;
        this.explanation = explanation;
    }

    public Photo(Username username, Image image, Explanation explanation) {
        this.username = username;
        this.image = image;
        this.explanation = explanation;
    }

    public Long getId() {
        return id;
    }

    public void change(Image image, Explanation explanation) {
        this.image = image;
        this.explanation = explanation;
    }

    public static Photo fake() {
        return new Photo(
                1L,
                new Username("test"),
                new Image("image_address"),
                new Explanation("이미지 설명")
        );
    }

    public PhotoDto toDto() {
        return new PhotoDto(
                id,
                image.getValue(),
                explanation.getValue(),
                createdAt);
    }

    public Username getUsername() {
        return username;
    }
}
