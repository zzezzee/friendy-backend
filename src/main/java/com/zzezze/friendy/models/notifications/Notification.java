package com.zzezze.friendy.models.notifications;

import com.zzezze.friendy.models.value_objects.Type;
import com.zzezze.friendy.models.value_objects.Username;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "sender"))
    private Username sender;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "receiver"))
    private Username receiver;

    @Embedded
    private Type type;

    private boolean checked = false;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Notification() {
    }

    public Notification(Username sender, Username receiver, Type type) {
        this.sender = sender;
        this.receiver = receiver;
        this.type = type;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public boolean isChecked() {
        return checked;
    }

    public Type getType() {
        return type;
    }

    public void check() {
        this.checked = true;
    }
}
