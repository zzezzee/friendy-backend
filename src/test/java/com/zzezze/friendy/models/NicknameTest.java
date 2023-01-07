package com.zzezze.friendy.models;

import com.zzezze.friendy.models.value_objects.Nickname;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class NicknameTest {
    @Test
    void equality() {
        Nickname writer1 = new Nickname("zzezze");
        Nickname writer2 = new Nickname("zzezze");

        assertThat(writer1).isEqualTo(writer2);
    }
}
