package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.PhotoDto;
import com.zzezze.friendy.dtos.PhotosDto;
import com.zzezze.friendy.exceptions.UserNotFound;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.Photo;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.repositories.PhotoRepository;
import com.zzezze.friendy.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class GetPhotosService {
    private final PhotoRepository photoRepository;
    private final UserRepository userRepository;

    public GetPhotosService(PhotoRepository photoRepository, UserRepository userRepository) {
        this.photoRepository = photoRepository;
        this.userRepository = userRepository;
    }

    public PhotosDto list(Nickname nickname) {
        User user = userRepository.findByNickname(nickname)
                .orElseThrow(UserNotFound::new);

        List<Photo> photos = photoRepository.findAllByUsername(user.getUsername());

        List<PhotoDto> photoDtos = photos.stream()
                .map(Photo::toDto)
                .toList();

        return new PhotosDto(photoDtos);
    }
}
