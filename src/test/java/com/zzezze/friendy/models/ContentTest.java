package com.zzezze.friendy.models;

import com.zzezze.friendy.models.value_objects.Content;
import com.zzezze.friendy.models.value_objects.Nickname;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ContentTest {
    @Test
    void equality() {
        Content content1 = new Content("내용");
        Content content2 = new Content("내용");

        assertThat(content1).isEqualTo(content2);
    }
}
