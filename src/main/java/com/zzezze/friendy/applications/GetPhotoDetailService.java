package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.PhotoDetailDto;
import com.zzezze.friendy.exceptions.PhotoNotFound;
import com.zzezze.friendy.models.Photo;
import com.zzezze.friendy.repositories.PhotoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GetPhotoDetailService {
    private final PhotoRepository photoRepository;

    public GetPhotoDetailService(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    public PhotoDetailDto detail(Long id) {
        Photo photo = photoRepository.findById(id)
                .orElseThrow(PhotoNotFound::new);

        return new PhotoDetailDto(
                photo.toDto()
        );
    }
}
