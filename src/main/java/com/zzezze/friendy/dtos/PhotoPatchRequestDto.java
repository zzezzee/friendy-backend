package com.zzezze.friendy.dtos;

public class PhotoPatchRequestDto {
    private Long id;
    private String image;
    private String explanation;

    public PhotoPatchRequestDto() {
    }

    public PhotoPatchRequestDto(Long id, String image, String explanation) {
        this.id = id;
        this.image = image;
        this.explanation = explanation;
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
}
