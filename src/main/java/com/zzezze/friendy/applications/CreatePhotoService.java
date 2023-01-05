package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.PhotoDto;
import com.zzezze.friendy.exceptions.UserNotFound;
import com.zzezze.friendy.models.value_objects.Explanation;
import com.zzezze.friendy.models.value_objects.Image;
import com.zzezze.friendy.models.Photo;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.PhotoRepository;
import com.zzezze.friendy.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CreatePhotoService {
    private final PhotoRepository photoRepository;
    private final UserRepository userRepository;

    public CreatePhotoService(PhotoRepository photoRepository, UserRepository userRepository) {
        this.photoRepository = photoRepository;
        this.userRepository = userRepository;
    }

    public PhotoDto create(Username username, Image image, Explanation explanation) {
        userRepository.findByUsername(username)
                .orElseThrow(UserNotFound::new);

        Photo photo = new Photo(username, image, explanation);

        Photo saved = photoRepository.save(photo);

        return saved.toDto();
    }
}
