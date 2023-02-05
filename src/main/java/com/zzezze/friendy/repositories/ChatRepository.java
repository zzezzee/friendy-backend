package com.zzezze.friendy.repositories;

import com.zzezze.friendy.models.Chat;
import com.zzezze.friendy.models.value_objects.ChatRoomId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChatRepository extends JpaRepository<Chat, Long> {
    List<Chat> findByChatRoomId(ChatRoomId chatRoomId);

    Optional<Chat> findTopByChatRoomIdOrderByCreatedAtDesc(ChatRoomId chatRoomId);
}
