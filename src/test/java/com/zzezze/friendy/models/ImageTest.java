package com.zzezze.friendy.models;

import com.zzezze.friendy.models.value_objects.Image;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ImageTest {
    @Test
    void equality() {
        Image image1 = new Image("image_address");
        Image image2 = new Image("image_address");

        assertThat(image1).isEqualTo(image2);
    }
}
