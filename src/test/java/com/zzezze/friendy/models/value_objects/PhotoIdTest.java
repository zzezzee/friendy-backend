package com.zzezze.friendy.models.value_objects;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PhotoIdTest {
    @Test
    void equality() {
        PhotoId photoId1 = new PhotoId(1L);
        PhotoId photoId2 = new PhotoId(1L);

        assertThat(photoId1).isEqualTo(photoId2);
    }
}
