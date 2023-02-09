package com.zzezze.friendy.models.value_objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class CommentId {
    @Column(name="comment_id")
    private Long value;

    public CommentId() {
    }

    public CommentId(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CommentId photoId = (CommentId) o;
        return Objects.equals(value, photoId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
