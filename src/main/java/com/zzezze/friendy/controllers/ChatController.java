package com.zzezze.friendy.controllers;

import com.zzezze.friendy.applications.GetChatsService;
import com.zzezze.friendy.dtos.ChatsDto;
import com.zzezze.friendy.models.value_objects.ChatRoomId;
import com.zzezze.friendy.models.value_objects.Username;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("chats")
public class ChatController {
    private GetChatsService getChatsService;

    public ChatController(GetChatsService getChatsService) {
        this.getChatsService = getChatsService;
    }

    @GetMapping
    public ChatsDto list(
            @RequestAttribute("username") Username username,
            @RequestParam Long chatRoomId
    ) {
            return getChatsService.list(username, new ChatRoomId(chatRoomId));
    }
}
