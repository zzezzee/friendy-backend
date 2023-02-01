package com.zzezze.friendy.controllers;

import com.zzezze.friendy.applications.GetChatRoomsService;
import com.zzezze.friendy.dtos.ChatRoomsDto;
import com.zzezze.friendy.models.value_objects.Username;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("chat-rooms")
public class ChatRoomController {
    private final GetChatRoomsService getChatRoomsService;

    public ChatRoomController(GetChatRoomsService getChatRoomsService) {
        this.getChatRoomsService = getChatRoomsService;
    }

    @GetMapping
    public ChatRoomsDto list(
            @RequestAttribute("username") Username username
    ) {
        return getChatRoomsService.list(username);
    }
}
