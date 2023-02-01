package com.zzezze.friendy.models;

import com.zzezze.friendy.models.value_objects.Username;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ChatRoomTest {
    @Test
    void containsMember() {
        ChatRoom chatRoom = ChatRoom.fake();

        Username username1 = new Username("test1");
        Username username2 = new Username("test2");

        boolean contains1 = chatRoom.containsMember(username1);
        boolean contains2 = chatRoom.containsMember(username2);

        assertTrue(contains1);
        assertTrue(contains2);
    }
}
