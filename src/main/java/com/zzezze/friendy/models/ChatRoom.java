package com.zzezze.friendy.models;

import com.zzezze.friendy.dtos.ChatRoomDto;
import com.zzezze.friendy.models.value_objects.Username;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

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

    public ChatRoom(Long id, Username host, Username guest) {
        this.id = id;
        this.host = host;
        this.guest = guest;
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

    public boolean containsMember(Username username) {
        if(!host.equals(username) && !guest.equals(username)){
            return false;
        }

        return true;
    }

    public static ChatRoom fake() {
        return new ChatRoom(
                1L,
                new Username("test1"),
                new Username("test2")
        );
    }

    public ChatRoomDto toDto() {
        return new ChatRoomDto(
                id,
                "image_address",
                "nickname",
                "최근채팅",
                LocalDateTime.now());
    }
}
