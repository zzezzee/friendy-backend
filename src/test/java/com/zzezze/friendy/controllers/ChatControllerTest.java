package com.zzezze.friendy.controllers;

import com.zzezze.friendy.applications.GetChatRoomsService;
import com.zzezze.friendy.applications.GetChatsService;
import com.zzezze.friendy.dtos.ChatRoomsDto;
import com.zzezze.friendy.dtos.ChatsDto;
import com.zzezze.friendy.models.ChatRoom;
import com.zzezze.friendy.models.value_objects.ChatRoomId;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ChatController.class)
class ChatControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetChatsService getChatsService;

    @SpyBean
    private JwtUtil jwtUtil;

    @Test
    void list() throws Exception {
        Username username = new Username("username");

        given(getChatsService.list(username, new ChatRoomId(1L)))
                .willReturn(ChatsDto.fake());

        String token = jwtUtil.encode(username.getValue());

        mockMvc.perform(MockMvcRequestBuilders.get("/chats?chatRoomId=1")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"chats\"")
                ));
    }

}
