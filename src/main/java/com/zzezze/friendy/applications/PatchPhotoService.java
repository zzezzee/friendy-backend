package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.PhotoDto;
import com.zzezze.friendy.exceptions.PatchPhotoFailed;
import com.zzezze.friendy.exceptions.PhotoNotFound;
import com.zzezze.friendy.exceptions.UserNotFound;
import com.zzezze.friendy.models.value_objects.Explanation;
import com.zzezze.friendy.models.value_objects.Image;
import com.zzezze.friendy.models.Photo;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.PhotoRepository;
import com.zzezze.friendy.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PatchPhotoService {
    private final PhotoRepository photoRepository;
    private final UserRepository userRepository;

    public PatchPhotoService(PhotoRepository photoRepository, UserRepository userRepository) {
        this.photoRepository = photoRepository;
        this.userRepository = userRepository;
    }

    public PhotoDto patch(Username username, Long id, Image image, Explanation explanation) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(UserNotFound::new);

        Photo photo = photoRepository.findById(id)
                .orElseThrow(PhotoNotFound::new);

        if(!user.getUsername().equals(photo.getUsername())){
            throw new PatchPhotoFailed();
        }

        photo.change(image, explanation);

        return photo.toDto();
    }
}
