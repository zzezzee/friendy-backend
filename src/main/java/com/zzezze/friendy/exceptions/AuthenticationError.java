package com.zzezze.friendy.exceptions;

public class AuthenticationError extends RuntimeException {
    public AuthenticationError() {
        super("Authentication error");
    }
}
