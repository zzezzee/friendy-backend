package com.zzezze.friendy.models.value_objects;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ParentIdTest {
    @Test
    void equality() {
        ParentId parentId1 = new ParentId(1L);
        ParentId parentId2 = new ParentId(1L);

        assertThat(parentId1).isEqualTo(parentId2);
    }
}
