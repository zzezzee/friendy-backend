package com.zzezze.friendy.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProfileImageTest {
    @Test
    void equality() {
        ProfileImage profileImage1 = new ProfileImage("image_address");
        ProfileImage profileImage2 = new ProfileImage("image_address");

        assertThat(profileImage2).isEqualTo(profileImage1);
    }
}
