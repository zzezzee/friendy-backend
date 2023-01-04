package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.PhotoDto;
import com.zzezze.friendy.exceptions.PatchPhotoFailed;
import com.zzezze.friendy.exceptions.PhotoNotFound;
import com.zzezze.friendy.exceptions.UserNotFound;
import com.zzezze.friendy.models.Explanation;
import com.zzezze.friendy.models.Image;
import com.zzezze.friendy.models.Photo;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.Username;
import com.zzezze.friendy.repositories.PhotoRepository;
import com.zzezze.friendy.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PatchPhotoService {
    private final UserRepository userRepository;
    private final PhotoRepository photoRepository;

    public PatchPhotoService(UserRepository userRepository, PhotoRepository photoRepository) {
        this.userRepository = userRepository;
        this.photoRepository = photoRepository;
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
