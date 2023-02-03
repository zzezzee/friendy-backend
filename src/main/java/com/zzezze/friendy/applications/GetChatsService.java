package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.ChatDto;
import com.zzezze.friendy.dtos.ChatsDto;
import com.zzezze.friendy.exceptions.ChatMemberNotMatched;
import com.zzezze.friendy.exceptions.ChatRoomNotFound;
import com.zzezze.friendy.exceptions.UserNotFound;
import com.zzezze.friendy.models.Chat;
import com.zzezze.friendy.models.ChatRoom;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.ChatRoomId;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.ChatRepository;
import com.zzezze.friendy.repositories.ChatRoomRepository;
import com.zzezze.friendy.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GetChatsService {
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;

    public GetChatsService(ChatRoomRepository chatRoomRepository, ChatRepository chatRepository, UserRepository userRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.chatRepository = chatRepository;
        this.userRepository = userRepository;
    }

    public ChatsDto list(Username username, ChatRoomId chatRoomId) {
        ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId.getValue())
                .orElseThrow(ChatRoomNotFound::new);

        if(!chatRoom.containsMember(username)){
            throw new ChatMemberNotMatched();
        }

        List<Chat> chats = chatRepository.findByChatRoomId(chatRoomId);

        List<ChatDto> chatDtos = new ArrayList<>();

        chats.forEach(chat -> {
            User user = userRepository.findByUsername(chat.getSender())
                    .orElseThrow(UserNotFound::new);

            chatDtos.add(chat.toDto(user.getNickname(), user.getProfileImage()));
        });

        return new ChatsDto(chatDtos);
    }
}
