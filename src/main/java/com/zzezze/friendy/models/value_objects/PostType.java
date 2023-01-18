package com.zzezze.friendy.models.value_objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class PostType {
    @Column(name="postType")
    private String value;

    public PostType() {
    }

    public PostType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostType postType = (PostType) o;
        return Objects.equals(value, postType.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}

