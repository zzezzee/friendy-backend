package com.zzezze.friendy.exceptions;

import java.util.function.Supplier;

public class ChatRoomNotFound extends RuntimeException {
    public ChatRoomNotFound() {
        super("ChatRoomNotFound");
    }
}
