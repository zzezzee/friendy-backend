package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.LikeNotificationDto;
import com.zzezze.friendy.dtos.NotificationsDto;
import com.zzezze.friendy.dtos.PhotoCommentNotificationDto;
import com.zzezze.friendy.exceptions.CommentNotFound;
import com.zzezze.friendy.exceptions.PhotoNotFound;
import com.zzezze.friendy.exceptions.UserNotFound;
import com.zzezze.friendy.models.Comment;
import com.zzezze.friendy.models.Photo;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.notifications.LikeNotification;
import com.zzezze.friendy.models.notifications.PhotoCommentNotification;
import com.zzezze.friendy.models.value_objects.Type;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.CommentRepository;
import com.zzezze.friendy.repositories.notifications.NotificationRepository;
import com.zzezze.friendy.repositories.PhotoRepository;
import com.zzezze.friendy.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class GetNotificationsService {
    private final NotificationRepository notificationRepository;
    private final PhotoRepository photoRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public GetNotificationsService(NotificationRepository notificationRepository, PhotoRepository photoRepository, CommentRepository commentRepository, UserRepository userRepository) {
        this.notificationRepository = notificationRepository;
        this.photoRepository = photoRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public NotificationsDto list(Username username) {
        List<PhotoCommentNotification> photoCommentNotifications
                = notificationRepository.findAllPhotoCommentNotificationByUsernameAndType(username, new Type("photoComment"));

        List<LikeNotification> likeNotifications
                = notificationRepository.findAllLikeNotificationByUsernameAndType(username, new Type("Like"));

        List <PhotoCommentNotificationDto> photoCommentNotificationDtos
                = photoCommentNotifications
                .stream()
                .map(photoCommentNotification -> {
                    User sender = userRepository.findByUsername(photoCommentNotification.getSender())
                            .orElseThrow(UserNotFound::new);
                    Photo photo = photoRepository.findById(photoCommentNotification.getPhotoId().getValue())
                            .orElseThrow(PhotoNotFound::new);
                    Comment comment = commentRepository.findById(photoCommentNotification.getCommentId().getValue())
                            .orElseThrow(CommentNotFound::new);

                    return photoCommentNotification.toDto(
                            photo.getId(),
                            sender.getNickname(),
                            photo.getImage(),
                            comment.getContent());
                })
                .toList();

        List <LikeNotificationDto> likeNotificationDtos
                = likeNotifications
                .stream()
                .map(likeNotification -> {
                    User sender = userRepository.findByUsername(likeNotification.getSender())
                            .orElseThrow(UserNotFound::new);
                    Photo photo = photoRepository.findById(likeNotification.getPhotoId().getValue())
                            .orElseThrow(PhotoNotFound::new);

                    return likeNotification.toDto(
                            photo.getId(),
                            sender.getNickname(),
                            photo.getImage());
                })
                .toList();

        return new NotificationsDto(photoCommentNotificationDtos, likeNotificationDtos);
    }
}
