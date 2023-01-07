package com.zzezze.friendy.models;

import com.zzezze.friendy.models.value_objects.Username;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UsernameTest {
    @Test
    void equality() {
        Username username1 = new Username("username");
        Username username2 = new Username("username");

        assertThat(username1).isEqualTo(username2);
    }
}
