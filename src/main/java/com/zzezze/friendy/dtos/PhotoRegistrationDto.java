package com.zzezze.friendy.dtos;

public class PhotoRegistrationDto {
    private String image;

    private String explanation;

    public PhotoRegistrationDto() {
    }

    public PhotoRegistrationDto(String image, String explanation) {
        this.image = image;
        this.explanation = explanation;
    }

    public String getImage() {
        return image;
    }

    public String getExplanation() {
        return explanation;
    }
}
