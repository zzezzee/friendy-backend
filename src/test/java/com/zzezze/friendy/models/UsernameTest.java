package com.zzezze.friendy.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class UsernameTest {
    @Test
    void equality() {
        Username username1 = new Username("username");
        Username username2 = new Username("username");

        assertThat(username1).isEqualTo(username2);
    }
}
