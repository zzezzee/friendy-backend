package com.zzezze.friendy.models.value_objects;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class PostId {
    private Long postId;

    public PostId() {
    }

    public PostId(Long postId) {
        this.postId = postId;
    }

    public Long getPostId() {
        return postId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PostId postId1 = (PostId) o;
        return Objects.equals(postId, postId1.postId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId);
    }
}
