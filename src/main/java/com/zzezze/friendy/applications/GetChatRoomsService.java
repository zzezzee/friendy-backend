package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.ChatRoomDto;
import com.zzezze.friendy.dtos.ChatRoomsDto;
import com.zzezze.friendy.exceptions.ChatNotFound;
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
public class GetChatRoomsService {
    private final ChatRoomRepository chatRoomRepository;
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;

    public GetChatRoomsService(ChatRoomRepository chatRoomRepository, UserRepository userRepository, ChatRepository chatRepository) {
        this.chatRoomRepository = chatRoomRepository;
        this.userRepository = userRepository;
        this.chatRepository = chatRepository;
    }

    public ChatRoomsDto list(Username username) {
        List<ChatRoom> chatRoomsCreatedByMe = chatRoomRepository.findAllByHost(username);
        List<ChatRoom> chatRoomsInvitedByOther = chatRoomRepository.findAllByGuest(username);

        List<ChatRoomDto> chatRoomDtos = new ArrayList<>();

        chatRoomsCreatedByMe.forEach(chatRoom -> {
                    User opponent = userRepository.findByUsername(chatRoom.getGuest()).orElseThrow(UserNotFound::new);

                    Chat chat = chatRepository.findTopByChatRoomIdOrderByCreatedAtDesc(new ChatRoomId(chatRoom.getId()))
                            .orElseThrow(ChatNotFound::new);

                    chatRoomDtos.add(new ChatRoomDto(
                            chatRoom.getId(),
                            opponent.getProfileImage().getValue(),
                            opponent.getNickname().getValue(),
                            chat.getContent().getValue(),
                            chat.getCreatedAt()
                    ));
                }
        );

        chatRoomsInvitedByOther.forEach(chatRoom -> {
                    User opponent = userRepository.findByUsername(chatRoom.getHost()).orElseThrow(UserNotFound::new);

                    Chat chat = chatRepository.findTopByChatRoomIdOrderByCreatedAtDesc(new ChatRoomId(chatRoom.getId()))
                            .orElseThrow(ChatNotFound::new);

                    chatRoomDtos.add(new ChatRoomDto(
                            chatRoom.getId(),
                            opponent.getProfileImage().getValue(),
                            opponent.getNickname().getValue(),
                            chat.getContent().getValue(),
                            chat.getCreatedAt()
                    ));
                }
        );

        return new ChatRoomsDto(chatRoomDtos);
    }
}
