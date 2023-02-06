package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.CommentDto;
import com.zzezze.friendy.dtos.LikeDto;
import com.zzezze.friendy.dtos.PhotoDetailDto;
import com.zzezze.friendy.exceptions.PhotoNotFound;
import com.zzezze.friendy.exceptions.UserNotFound;
import com.zzezze.friendy.models.Comment;
import com.zzezze.friendy.models.Like;
import com.zzezze.friendy.models.Photo;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.PhotoId;
import com.zzezze.friendy.repositories.CommentRepository;
import com.zzezze.friendy.repositories.LikeRepository;
import com.zzezze.friendy.repositories.PhotoRepository;
import com.zzezze.friendy.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class GetPhotoDetailService {
    private final PhotoRepository photoRepository;
    private final LikeRepository likeRepository;

    public GetPhotoDetailService(PhotoRepository photoRepository, LikeRepository likeRepository) {
        this.photoRepository = photoRepository;
        this.likeRepository = likeRepository;
    }

    public PhotoDetailDto detail(Long id) {
        Photo photo = photoRepository.findById(id)
                .orElseThrow(PhotoNotFound::new);

        List<Like> likes = likeRepository.findAllByPhotoId(new PhotoId(id));

        List<LikeDto> likeDtos = likes.stream().map(Like::toDto).toList();

        return new PhotoDetailDto(
                photo.toDto(),
                likeDtos
        );
    }
}
