package com.zzezze.friendy.applications;

import com.zzezze.friendy.repositories.ChatRoomRepository;
import com.zzezze.friendy.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class GetChatRoomsServiceTest {
    ChatRoomRepository chatRoomRepository;
    UserRepository userRepository;
    GetChatRoomsService getChatRoomsService;

    @BeforeEach
    void setup() {
        chatRoomRepository = mock(ChatRoomRepository.class);
        userRepository = mock(UserRepository.class);
        getChatRoomsService = new GetChatRoomsService(chatRoomRepository, userRepository);
    }

    @Test
    void list() {

    }
}
