package com.zzezze.friendy.models;

import com.zzezze.friendy.models.value_objects.Username;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
public class Invitation {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "sender"))
    private Username sender;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "receiver"))
    private Username receiver;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Invitation() {
    }

    public Invitation(Long id, Username sender, Username receiver) {
        this.id = id;
        this.sender = sender;
        this.receiver = receiver;
    }

    public Invitation(Username sender, Username receiver) {
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

    public Relationship accept() {
        return new Relationship(sender, receiver);
    }

    public static Invitation of(Username sender, Username receiver) {
        return new Invitation(sender, receiver);
    }

    public static Invitation fake() {
        return new Invitation(
                1L,
                new Username("test1"),
                new Username("test2")
        );
    }
}
