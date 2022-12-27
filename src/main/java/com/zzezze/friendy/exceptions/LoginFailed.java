package com.zzezze.friendy.exceptions;

public class LoginFailed extends RuntimeException{
    public LoginFailed() {
        super("LoginFailed");
    }
}
