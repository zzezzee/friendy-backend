package com.zzezze.friendy.models;

import com.zzezze.friendy.models.value_objects.Nickname;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RelationShipTest {
    @Test
    void discriminateMe() {
        RelationShip relationShip = new RelationShip();

        User user1 = User.fake();
        Nickname nickname = new Nickname("zzezze");

        String result = relationShip.discriminate(user1.getNickname(), nickname);

        assertThat(result).isEqualTo("me");
    }
}
