package com.zzezze.friendy.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MiniHomepageTest {
    @Test
    void creation() {
        MiniHomepage miniHomepage = MiniHomepage.fake();

        assertThat(miniHomepage).isNotNull();
    }
}
