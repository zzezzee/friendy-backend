package com.zzezze.friendy.models.value_objects;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class ParentId {
    @Column(name="parentId")
    private Long value;

    public ParentId() {
    }

    public ParentId(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParentId parentId = (ParentId) o;
        return Objects.equals(value, parentId.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
