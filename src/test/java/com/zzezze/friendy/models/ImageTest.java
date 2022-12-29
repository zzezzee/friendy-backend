package com.zzezze.friendy.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ImageTest {
    @Test
    void equality() {
        Image image1 = new Image("image_address");
        Image image2 = new Image("image_address");

        assertThat(image1).isEqualTo(image2);
    }
}
