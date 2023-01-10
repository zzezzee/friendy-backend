package com.zzezze.friendy.models;

import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.value_objects.Username;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

@Entity
public class Relationship {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "sender"))
    private Username sender;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "receiver"))
    private Username receiver;

//    private List<User> friends

    public Relationship() {
    }

    public Relationship(Long id, Username user, Username otherUser) {
        this.id = id;
        this.sender = user;
        this.receiver = otherUser;
    }

    public Relationship(Username user, Username otherUser) {
        this.sender = user;
        this.receiver = otherUser;
    }

    public Long getId() {
        return id;
    }

    public Username getSender() {
        return sender;
    }

    public Username getReceiver() {
        return receiver;
    }

    public static Relationship fake() {
        return new Relationship(
                1L,
                new Username("zzezze"),
                new Username("sunghwan")
                );
    }
}
