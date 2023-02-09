package com.zzezze.friendy.applications;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zzezze.friendy.applications.notifications.LikeNotificationService;
import com.zzezze.friendy.exceptions.LikeNotificationNotFound;
import com.zzezze.friendy.exceptions.PhotoNotFound;
import com.zzezze.friendy.models.Like;
import com.zzezze.friendy.models.Photo;
import com.zzezze.friendy.models.notifications.LikeNotification;
import com.zzezze.friendy.models.value_objects.PhotoId;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.LikeRepository;
import com.zzezze.friendy.repositories.PhotoRepository;
import com.zzezze.friendy.repositories.notifications.LikeNotificationRepository;
import com.zzezze.friendy.repositories.notifications.NotificationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PatchLikeService {
    private final LikeRepository likeRepository;
    private final LikeNotificationService notificationService;
    private final PhotoRepository photoRepository;
    private final LikeNotificationRepository likeNotificationRepository;
    private final NotificationRepository notificationRepository;

    public PatchLikeService(LikeRepository likeRepository, LikeNotificationService notificationService, PhotoRepository photoRepository, LikeNotificationRepository likeNotificationRepository, NotificationRepository notificationRepository) {
        this.likeRepository = likeRepository;
        this.notificationService = notificationService;
        this.photoRepository = photoRepository;
        this.likeNotificationRepository = likeNotificationRepository;
        this.notificationRepository = notificationRepository;
    }

    public void patch(Username username, PhotoId photoId) throws JsonProcessingException {
        if (likeRepository.existsByPhotoIdAndUsername(photoId, username)) {
            likeRepository.deleteByPhotoIdAndUsername(photoId, username);

            if(likeNotificationRepository.existsByPhotoId(photoId)){
                LikeNotification likeNotification = likeNotificationRepository.findByPhotoId(photoId)
                                .orElseThrow(LikeNotificationNotFound::new);

                notificationRepository.deleteById(likeNotification.getId());

                likeNotificationRepository.deleteByPhotoId(photoId);
            }
            return;
        }

        if (!likeRepository.existsByPhotoIdAndUsername(photoId, username)) {
            Like like = new Like(username, photoId);
            likeRepository.save(like);

            Photo photo = photoRepository.findById(photoId.getValue())
                    .orElseThrow(PhotoNotFound::new);

            notificationService.sendNotification(username, photo.getUsername(), photo);
        }
    }
}
