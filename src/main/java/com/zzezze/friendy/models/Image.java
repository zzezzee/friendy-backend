package com.zzezze.friendy.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Image {
    @Column(name = "image")
    private String value;

    public Image() {
    }

    public Image(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image = (Image) o;
        return Objects.equals(value, image.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
