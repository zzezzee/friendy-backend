package com.zzezze.friendy.models;

import com.zzezze.friendy.models.value_objects.Nickname;
import org.springframework.stereotype.Component;

@Component
public class RelationShip {
    public String discriminate(Nickname nickname1, Nickname nickname2) {
        if (nickname1.equals(nickname2)) {
            return "me";
        }

        return "stranger";
    }
}
