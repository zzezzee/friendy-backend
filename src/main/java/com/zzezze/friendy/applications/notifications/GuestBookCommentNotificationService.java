package com.zzezze.friendy.applications.notifications;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.zzezze.friendy.dtos.PhotoCommentNotificationDto;
import com.zzezze.friendy.exceptions.UserNotFound;
import com.zzezze.friendy.models.Comment;
import com.zzezze.friendy.models.GuestBook;
import com.zzezze.friendy.models.Photo;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.notifications.Notification;
import com.zzezze.friendy.models.notifications.PhotoCommentNotification;
import com.zzezze.friendy.models.value_objects.CommentId;
import com.zzezze.friendy.models.value_objects.PhotoId;
import com.zzezze.friendy.models.value_objects.Type;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.EmitterRepository;
import com.zzezze.friendy.repositories.UserRepository;
import com.zzezze.friendy.repositories.notifications.NotificationRepository;
import com.zzezze.friendy.utils.CustomLocalDateTimeSerializer;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

@Service
@Transactional
public class GuestBookCommentNotificationService {
    private final EmitterRepository emitterRepository;
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    public GuestBookCommentNotificationService(EmitterRepository emitterRepository, UserRepository userRepository,
                                               NotificationRepository notificationRepository) {
        this.emitterRepository = emitterRepository;
        this.userRepository = userRepository;
        this.notificationRepository = notificationRepository;
    }

    public void sendNotification(Username sender, Username receiver, GuestBook guestBook, Comment comment) throws JsonProcessingException {
        PhotoCommentNotification photoCommentNotification = new PhotoCommentNotification(
                sender,
                receiver,
                new Type("GuestBookComment"),
                new PhotoId(guestBook.getId()),
                new CommentId(comment.getId())
        );

        PhotoCommentNotification notification = notificationRepository.save(photoCommentNotification);

        Map<Username, SseEmitter> sseEmitters = emitterRepository.findByUsername(receiver);

        String data = transferToDto(notification, guestBook, comment);

        sseEmitters.forEach(
                (key, emitter) -> {
                    sendToClient(emitter, key, data);
                }
        );
    }

    private void sendToClient(SseEmitter emitter, Username username, String data) {
        try {
            emitter.send(SseEmitter.event()
                    .id(username.getValue())
                    .name("client")
                    .data(data));
        } catch (IOException exception) {
            emitterRepository.deleteById(username);
            throw new RuntimeException("연결 오류");
        }
    }

    private String transferToDto(Notification notification, GuestBook guestBook, Comment comment) throws JsonProcessingException {
        User sender = userRepository.findByUsername(notification.getSender())
                .orElseThrow(UserNotFound::new);

        PhotoCommentNotificationDto photoCommentNotificationDto =
                new PhotoCommentNotificationDto(
                        notification.getId(),
                        notification.isChecked(),
                        LocalDateTime.now(),
                        guestBook.getId(),
                        sender.getNickname().getValue(),
                        sender.getProfileImage().getValue(),
                        comment.getContent().getValue(),
                        notification.getType().getValue()
                );

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(LocalDateTime.class, new CustomLocalDateTimeSerializer());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(simpleModule);

        return objectMapper.writeValueAsString(photoCommentNotificationDto);
    }
}
