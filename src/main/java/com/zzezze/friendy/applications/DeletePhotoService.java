package com.zzezze.friendy.applications;


import com.zzezze.friendy.dtos.PhotoDeleteResponseDto;
import com.zzezze.friendy.exceptions.PhotoDeleteFailed;
import com.zzezze.friendy.exceptions.PhotoNotFound;
import com.zzezze.friendy.exceptions.UserNotFound;
import com.zzezze.friendy.models.Photo;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.PhotoRepository;
import com.zzezze.friendy.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DeletePhotoService {
    private final PhotoRepository photoRepository;
    private final UserRepository userRepository;

    public DeletePhotoService(PhotoRepository photoRepository, UserRepository userRepository) {
        this.photoRepository = photoRepository;
        this.userRepository = userRepository;
    }

    public PhotoDeleteResponseDto delete(Username username, Long id) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(UserNotFound::new);

        Photo photo = photoRepository.findById(id)
                .orElseThrow(PhotoNotFound::new);

        if(!photo.getUsername().equals(user.getUsername())){
            throw new PhotoDeleteFailed();
        }

        photoRepository.deleteById(id);

        return new PhotoDeleteResponseDto(id);
    }
}
