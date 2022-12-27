package com.zzezze.friendy.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    @Test
    void create() {
        User user = User.fake();

        assertThat(user.getNickname().getValue()).isEqualTo("zzezze");
    }
}
