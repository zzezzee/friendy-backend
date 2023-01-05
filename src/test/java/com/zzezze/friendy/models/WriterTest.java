package com.zzezze.friendy.models;

import com.zzezze.friendy.models.value_objects.Writer;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class WriterTest {
    @Test
    void equality() {
        Writer nickname1 = new Writer("zzezze");
        Writer nickname2 = new Writer("zzezze");

        assertThat(nickname1).isEqualTo(nickname2);
    }
}
