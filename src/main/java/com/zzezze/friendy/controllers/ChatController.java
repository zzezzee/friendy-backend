package com.zzezze.friendy.controllers;

import com.zzezze.friendy.applications.CreateChatService;
import com.zzezze.friendy.applications.GetChatsService;
import com.zzezze.friendy.dtos.ChatRequestDto;
import com.zzezze.friendy.dtos.ChatsDto;
import com.zzezze.friendy.models.value_objects.ChatRoomId;
import com.zzezze.friendy.models.value_objects.Content;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.value_objects.Username;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class ChatController {
    private final GetChatsService getChatsService;
    private final CreateChatService createChatService;

    public ChatController(GetChatsService getChatsService, CreateChatService createChatService) {
        this.getChatsService = getChatsService;
        this.createChatService = createChatService;
    }

    @GetMapping("/chats")
    public ChatsDto list(
            @RequestAttribute("username") Username username,
            @RequestParam Long chatRoomId
    ) {
            return getChatsService.list(username, new ChatRoomId(chatRoomId));
    }

    @MessageMapping("/chats")
    public void message(
            @RequestBody ChatRequestDto chatRequestDto
    ) {
        Nickname nickname = new Nickname(chatRequestDto.getNickname());
        ChatRoomId chatRoomId = new ChatRoomId(chatRequestDto.getChatRoomId());
        Content content = new Content(chatRequestDto.getContent());

        createChatService.create(nickname, chatRoomId, content);
    }
}
