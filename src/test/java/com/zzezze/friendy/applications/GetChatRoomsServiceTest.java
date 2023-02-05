package com.zzezze.friendy.applications;

import com.zzezze.friendy.repositories.ChatRepository;
import com.zzezze.friendy.repositories.ChatRoomRepository;
import com.zzezze.friendy.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

class GetChatRoomsServiceTest {
    ChatRoomRepository chatRoomRepository;
    UserRepository userRepository;
    ChatRepository chatRepository;
    GetChatRoomsService getChatRoomsService;

    @BeforeEach
    void setup() {
        chatRoomRepository = mock(ChatRoomRepository.class);
        userRepository = mock(UserRepository.class);
        chatRepository = mock(ChatRepository.class);
        getChatRoomsService = new GetChatRoomsService(chatRoomRepository, userRepository, chatRepository);
    }

    @Test
    void list() {

    }
}
