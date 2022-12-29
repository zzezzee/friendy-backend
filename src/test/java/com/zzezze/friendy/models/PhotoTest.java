package com.zzezze.friendy.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PhotoTest {
    @Test
    void creation() {
        Photo photo = Photo.fake();

        assertThat(photo).isNotNull();
    }
}
