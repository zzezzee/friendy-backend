package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.ChatsDto;
import com.zzezze.friendy.models.Chat;
import com.zzezze.friendy.models.ChatRoom;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.ChatRoomId;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.ChatRepository;
import com.zzezze.friendy.repositories.ChatRoomRepository;
import com.zzezze.friendy.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetChatsServiceTest {
    ChatRoomRepository chatRoomRepository;
    ChatRepository chatRepository;
    UserRepository userRepository;
    GetChatsService getChatsService;

    @BeforeEach
    void setup() {
        chatRoomRepository = mock(ChatRoomRepository.class);
        chatRepository = mock(ChatRepository.class);
        userRepository = mock(UserRepository.class);

        getChatsService = new GetChatsService(chatRoomRepository, chatRepository, userRepository);
    }

    @Test
    void list() {
        Username username = new Username("test1");
        ChatRoomId chatRoomId = new ChatRoomId(1L);

        given(chatRoomRepository.findById(chatRoomId.getValue()))
                .willReturn(Optional.of(ChatRoom.fake()));

        given(chatRepository.findByChatRoomId(chatRoomId))
                .willReturn(List.of(Chat.fake()));

        given(userRepository.findByUsername(username))
                .willReturn(Optional.of(User.fake()));

        ChatsDto chatsDto = getChatsService.list(username, chatRoomId);

        assertThat(chatsDto.getChats()).hasSize(1);
    }
}
