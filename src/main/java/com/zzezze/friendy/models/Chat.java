package com.zzezze.friendy.models;

import com.zzezze.friendy.dtos.ChatDto;
import com.zzezze.friendy.models.value_objects.ChatRoomId;
import com.zzezze.friendy.models.value_objects.Content;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.value_objects.ProfileImage;
import com.zzezze.friendy.models.value_objects.Username;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class Chat {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private ChatRoomId chatRoomId;

    @Embedded
    private Username sender;

    @Embedded
    private Content content;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Chat() {
    }

    public Chat(Long id, ChatRoomId chatRoomId, Username sender, Content content, LocalDateTime createdAt) {
        this.id = id;
        this.chatRoomId = chatRoomId;
        this.sender = sender;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Username getSender() {
        return sender;
    }

    public static Chat fake() {
        return new Chat(
                1L,
                new ChatRoomId(1L),
                new Username("test1"),
                new Content("채팅내용"),
                LocalDateTime.now()
        );
    }

    public ChatDto toDto(Nickname nickname, ProfileImage profileImage) {
        return new ChatDto(
                id,
                profileImage.getValue(),
                nickname.getValue(),
                content.getValue(),
                createdAt
        );
    }
}
