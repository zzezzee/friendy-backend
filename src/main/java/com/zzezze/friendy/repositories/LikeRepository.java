package com.zzezze.friendy.repositories;

import com.zzezze.friendy.models.Like;
import com.zzezze.friendy.models.value_objects.PhotoId;
import com.zzezze.friendy.models.value_objects.Username;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {
    boolean existsByPhotoIdAndUsername(PhotoId photoId, Username username);

    void deleteByPhotoIdAndUsername(PhotoId photoId, Username username);

    List<Like> findAllByPhotoId(PhotoId photoId);
}
