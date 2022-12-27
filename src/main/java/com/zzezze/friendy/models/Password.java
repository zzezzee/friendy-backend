package com.zzezze.friendy.models;

import com.zzezze.friendy.exceptions.PasswordDoNotMatches;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Transient;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Objects;

@Embeddable
public class Password {
    @Column(name = "password")
    private String value;

    @Transient
    private final PasswordEncoder passwordEncoder = new Argon2PasswordEncoder(32, 32, 1, 16384, 1);

    public Password() {
    }

    public Password(String value) {
        this.value = value;
    }

    public Password encode() {
        return new Password(passwordEncoder.encode(value));
    }

    public void match(Password password) {
        if (!passwordEncoder.matches(password.value, this.value)) {
            throw new PasswordDoNotMatches();
        }
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        Password password = (Password) other;

        return Objects.equals(value, password.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
