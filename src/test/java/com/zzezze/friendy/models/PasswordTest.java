package com.zzezze.friendy.models;

import com.zzezze.friendy.models.value_objects.Password;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class PasswordTest {
    @Test
    void equality() {
        Password password1 = new Password("Password1234!");
        Password password2 = new Password("Password1234!");

        assertThat(password2).isEqualTo(password1);
    }
}
