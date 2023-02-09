package com.zzezze.friendy.applications.notifications;

import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.EmitterRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@Service
@Transactional
public class NotificationService {
    private final EmitterRepository emitterRepository;

    public NotificationService(EmitterRepository emitterRepository) {
        this.emitterRepository = emitterRepository;
    }

    public SseEmitter subscribe(Username username) {
        SseEmitter emitter = emitterRepository.save(username, new SseEmitter(60L * 1000 * 60));

        emitter.onCompletion(() -> emitterRepository.deleteById(username));
        emitter.onTimeout(() -> emitterRepository.deleteById(username));

        return emitter;
    }
}
