package com.zzezze.friendy.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class NicknameTest {
    @Test
    void equality() {
        Nickname nickname1 = new Nickname("zzezze");
        Nickname nickname2 = new Nickname("zzezze");

        assertThat(nickname1).isEqualTo(nickname2);
    }
}
