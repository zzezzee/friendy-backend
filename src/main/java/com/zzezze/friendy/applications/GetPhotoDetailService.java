package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.CommentDto;
import com.zzezze.friendy.dtos.PhotoDetailDto;
import com.zzezze.friendy.exceptions.PhotoNotFound;
import com.zzezze.friendy.exceptions.UserNotFound;
import com.zzezze.friendy.models.Comment;
import com.zzezze.friendy.models.Photo;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.PhotoId;
import com.zzezze.friendy.repositories.CommentRepository;
import com.zzezze.friendy.repositories.PhotoRepository;
import com.zzezze.friendy.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class GetPhotoDetailService {
    private final PhotoRepository photoRepository;
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public GetPhotoDetailService(PhotoRepository photoRepository, CommentRepository commentRepository, UserRepository userRepository) {
        this.photoRepository = photoRepository;
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public PhotoDetailDto detail(Long id) {
        Photo photo = photoRepository.findById(id)
                .orElseThrow(PhotoNotFound::new);

        return new PhotoDetailDto(
                photo.toDto()
        );
    }
}
