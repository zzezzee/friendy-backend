package com.zzezze.friendy.models;

import com.zzezze.friendy.dtos.ChatRoomDto;
import com.zzezze.friendy.models.value_objects.ProfileImage;
import com.zzezze.friendy.models.value_objects.Username;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class ChatRoom {
    @Id
    @GeneratedValue
    private Long id;

    @AttributeOverride(name = "value", column = @Column(name = "host"))
    private Username host;

    @AttributeOverride(name = "value", column = @Column(name = "guest"))
    private Username guest;

    public ChatRoom() {
    }

    public Long getId() {
        return id;
    }

    public Username getHost() {
        return host;
    }

    public Username getGuest() {
        return guest;
    }

    public static ChatRoom fake() {
        return new ChatRoom();
    }

    public ChatRoomDto toDto() {
        return new ChatRoomDto(
                id,
                "image_address",
                "nickname");
    }
}
