package com.zzezze.friendy.dtos;

import java.util.List;

public class ChatRoomsDto {
    private List<ChatRoomDto> chatRooms;

    public ChatRoomsDto(List<ChatRoomDto> chatRooms) {
        this.chatRooms = chatRooms;
    }

    public List<ChatRoomDto> getChatRooms() {
        return chatRooms;
    }
}
