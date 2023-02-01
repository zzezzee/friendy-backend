package com.zzezze.friendy.repositories;

import com.zzezze.friendy.models.ChatRoom;
import com.zzezze.friendy.models.value_objects.Username;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatRoomRepository extends JpaRepository<ChatRoom, Long> {
    List<ChatRoom> findAllByHost(Username username);

    List<ChatRoom> findAllByGuest(Username username);
}
