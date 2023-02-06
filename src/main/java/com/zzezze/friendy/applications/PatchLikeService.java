package com.zzezze.friendy.applications;

import com.zzezze.friendy.models.Like;
import com.zzezze.friendy.models.value_objects.PhotoId;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.LikeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PatchLikeService {
    private final LikeRepository likeRepository;

    public PatchLikeService(LikeRepository likeRepository) {
        this.likeRepository = likeRepository;
    }

    public void patch(Username username, PhotoId photoId) {
        if (likeRepository.existsByPhotoIdAndUsername(photoId, username)) {
            likeRepository.deleteByPhotoIdAndUsername(photoId, username);
            return;
        }

        if (!likeRepository.existsByPhotoIdAndUsername(photoId, username)) {
            likeRepository.save(new Like(username, photoId));
        }
    }
}
