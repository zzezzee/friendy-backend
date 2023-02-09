package com.zzezze.friendy.models.value_objects;

import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class GuestBookId {
    private Long value;

    public GuestBookId() {
    }

    public GuestBookId(Long value) {
        this.value = value;
    }

    public Long getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GuestBookId that = (GuestBookId) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
