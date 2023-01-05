package com.zzezze.friendy.models;

import com.zzezze.friendy.models.value_objects.Explanation;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ExplanationTest {
    @Test
    void equality() {
        Explanation explanation1 = new Explanation("사진설명");
        Explanation explanation2 = new Explanation("사진설명");

        assertThat(explanation1).isEqualTo(explanation2);
    }
}
