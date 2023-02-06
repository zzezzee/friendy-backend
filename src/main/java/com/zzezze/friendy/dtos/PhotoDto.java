package com.zzezze.friendy.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class PhotoDto {
    private Long id;

    private String image;

    private String explanation;

    private LocalDateTime createdAt;

    public PhotoDto() {
    }

    public PhotoDto(Long id, String image, String explanation, LocalDateTime createdAt) {
        this.id = id;
        this.image = image;
        this.explanation = explanation;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getExplanation() {
        return explanation;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
