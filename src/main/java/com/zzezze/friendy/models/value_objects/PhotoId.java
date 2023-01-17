package com.zzezze.friendy.models.value_objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class PhotoId {
    @Column(name="photoId")
    private Long value;

    public PhotoId() {
    }

    public PhotoId(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PhotoId photoId = (PhotoId) o;
        return Objects.equals(value, photoId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
