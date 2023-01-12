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

import static jakarta.persistence.GenerationType.IDENTITY;

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

    public Relationship() {
    }

    public Relationship(Long id, Username sender, Username receiver) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
    }

    public Relationship(Username sender, Username receiver) {
        this.sender = sender;
        this.receiver = receiver;
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
