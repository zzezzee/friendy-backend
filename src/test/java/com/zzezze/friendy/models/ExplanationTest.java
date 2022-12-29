package com.zzezze.friendy.models;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ExplanationTest {
    @Test
    void equality() {
        Explanation explanation1 = new Explanation("사진설명");
        Explanation explanation2 = new Explanation("사진설명");

        assertThat(explanation1).isEqualTo(explanation2);
    }
}
