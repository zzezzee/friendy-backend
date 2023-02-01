package com.zzezze.friendy.dtos;

import java.util.List;

public class ChatsDto {
    private List<ChatDto> chats;

    public ChatsDto(List<ChatDto> chats) {
        this.chats = chats;
    }

    public List<ChatDto> getChats() {
        return chats;
    }

    public static ChatsDto fake() {
        return new ChatsDto(List.of(ChatDto.fake()));
    }
}
