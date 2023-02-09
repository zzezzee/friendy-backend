package com.zzezze.friendy.repositories;


import com.zzezze.friendy.models.notifications.Notification;
import com.zzezze.friendy.models.value_objects.Username;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Repository
public class EmitterRepository {
    public final Map<Username, SseEmitter> emitters = new ConcurrentHashMap<>();

    public SseEmitter save(Username id, SseEmitter sseEmitter){
        emitters.put(id, sseEmitter);

        System.out.println("****************");
        for (Username key: emitters.keySet()){
            System.out.println("key = " + key.getValue() + "value = " + emitters.get(key) );
        }
        System.out.println("****************");

        return sseEmitter;
    }

    public void deleteById(Username username){
        emitters.remove(username);
    }

    public Map<Username, SseEmitter> findByUsername(Username username) {
        return emitters.entrySet().stream()
                .filter(entry -> entry.getKey().equals(username))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
