package com.zzezze.friendy.controllers;

import com.zzezze.friendy.applications.GetChatRoomsService;
import com.zzezze.friendy.dtos.ChatRoomsDto;
import com.zzezze.friendy.models.ChatRoom;
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
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ChatRoomController.class)
class ChatRoomControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetChatRoomsService getChatRoomsService;

    @SpyBean
    private JwtUtil jwtUtil;

    @Test
    void list() throws Exception {
        Username username = new Username("username");

        given(getChatRoomsService.list(username))
                .willReturn(new ChatRoomsDto(List.of(ChatRoom.fake().toDto())));

        String token = jwtUtil.encode(username.getValue());

        mockMvc.perform(MockMvcRequestBuilders.get("/chat-rooms")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"chatRooms\"")
                ));
    }
}
