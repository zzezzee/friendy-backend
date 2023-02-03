package com.zzezze.friendy.applications;

import com.zzezze.friendy.exceptions.UserNotFound;
import com.zzezze.friendy.models.Chat;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.ChatRoomId;
import com.zzezze.friendy.models.value_objects.Content;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.repositories.ChatRepository;
import com.zzezze.friendy.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CreateChatService {
    private final UserRepository userRepository;
    private final ChatRepository chatRepository;

    private final SimpMessagingTemplate simpMessagingTemplate;

    public CreateChatService(UserRepository userRepository, ChatRepository chatRepository, SimpMessagingTemplate simpMessagingTemplate) {
        this.userRepository = userRepository;
        this.chatRepository = chatRepository;
        this.simpMessagingTemplate = simpMessagingTemplate;
    }

    public void create(Nickname nickname, ChatRoomId chatRoomId, Content content) {
        User user = userRepository.findByNickname(nickname)
                .orElseThrow(UserNotFound::new);

        Chat chat = new Chat(chatRoomId, user.getUsername(), content);

        Chat savedChat = chatRepository.save(chat);

        simpMessagingTemplate.convertAndSend(
                "/sub/chat-rooms/" + chatRoomId.getValue(),
                savedChat.toDto(user.getNickname(), user.getProfileImage())
        );
    }
}
