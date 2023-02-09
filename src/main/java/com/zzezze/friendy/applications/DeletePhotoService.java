package com.zzezze.friendy.applications;


import com.zzezze.friendy.dtos.PhotoDeleteResponseDto;
import com.zzezze.friendy.exceptions.PhotoCommentNotificationNotFound;
import com.zzezze.friendy.exceptions.PhotoDeleteFailed;
import com.zzezze.friendy.exceptions.PhotoNotFound;
import com.zzezze.friendy.exceptions.UserNotFound;
import com.zzezze.friendy.models.Photo;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.notifications.PhotoCommentNotification;
import com.zzezze.friendy.models.value_objects.CommentId;
import com.zzezze.friendy.models.value_objects.PhotoId;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.NotificationRepository;
import com.zzezze.friendy.repositories.PhotoCommentNotificationRepository;
import com.zzezze.friendy.repositories.PhotoRepository;
import com.zzezze.friendy.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DeletePhotoService {
    private final PhotoRepository photoRepository;
    private final UserRepository userRepository;
    private final PhotoCommentNotificationRepository photoCommentNotificationRepository;
    private final NotificationRepository notificationRepository;

    public DeletePhotoService(PhotoRepository photoRepository, UserRepository userRepository, PhotoCommentNotificationRepository photoCommentNotificationRepository, NotificationRepository notificationRepository) {
        this.photoRepository = photoRepository;
        this.userRepository = userRepository;
        this.photoCommentNotificationRepository = photoCommentNotificationRepository;
        this.notificationRepository = notificationRepository;
    }

    public PhotoDeleteResponseDto delete(Username username, Long id) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(UserNotFound::new);

        Photo photo = photoRepository.findById(id)
                .orElseThrow(PhotoNotFound::new);

        if (!photo.getUsername().equals(user.getUsername())) {
            throw new PhotoDeleteFailed();
        }

        if (photoCommentNotificationRepository.existsByPhotoId(new PhotoId(photo.getId()))) {
            PhotoCommentNotification photoCommentNotification =
                    photoCommentNotificationRepository.findByPhotoId(new PhotoId(photo.getId()))
                            .orElseThrow(PhotoCommentNotificationNotFound::new);

            notificationRepository.deleteById(photoCommentNotification.getId());
            photoCommentNotificationRepository.delete(photoCommentNotification);
        }


        photoRepository.deleteById(id);

        return new PhotoDeleteResponseDto(id);
    }
}
