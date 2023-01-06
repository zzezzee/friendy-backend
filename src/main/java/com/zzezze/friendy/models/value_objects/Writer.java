package com.zzezze.friendy.models.value_objects;

import com.zzezze.friendy.models.dtos.WriterDto;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class Writer {
    @Column(name = "writer_username")
    private String value;

    public Writer() {
    }

    public Writer(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Writer writer = (Writer) o;
        return Objects.equals(value, writer.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
