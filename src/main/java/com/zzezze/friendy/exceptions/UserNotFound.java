package com.zzezze.friendy.exceptions;

public class UserNotFound extends RuntimeException{
    public UserNotFound() {
        super("UserNotFound");
    }
}
