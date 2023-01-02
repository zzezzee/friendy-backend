package com.zzezze.friendy.models;

import com.zzezze.friendy.dtos.PhotoDto;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

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
                explanation.getValue()
        );
    }

    public Username getUsername() {
        return username;
    }
}
