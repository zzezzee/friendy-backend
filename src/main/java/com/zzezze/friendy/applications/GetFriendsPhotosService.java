package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.FriendsPhotoDto;
import com.zzezze.friendy.dtos.FriendsPhotosDto;
import com.zzezze.friendy.dtos.UserDto;
import com.zzezze.friendy.exceptions.UserNotFound;
import com.zzezze.friendy.models.Photo;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.PhotoRepository;
import com.zzezze.friendy.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class GetFriendsPhotosService {
    private final UserRepository userRepository;
    private final GetRelationshipService getRelationshipService;
    private PhotoRepository photoRepository;

    public GetFriendsPhotosService(UserRepository userRepository, GetRelationshipService getRelationshipService, PhotoRepository photoRepository) {
        this.userRepository = userRepository;
        this.getRelationshipService = getRelationshipService;
        this.photoRepository = photoRepository;
    }

    public FriendsPhotosDto list(Username username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(UserNotFound::new);

        List<UserDto> users = getRelationshipService.list(user.getNickname()).getUsers();

        List<FriendsPhotoDto> friendsPhotoDtos = new ArrayList<>();

        users.stream().forEach(userDto -> {
            User friend = userRepository.findById(userDto.getId())
                    .orElseThrow(UserNotFound::new);

            List<Photo> photos = photoRepository.findAllByUsername(friend.getUsername());

            photos.stream().forEach(photo -> {
                friendsPhotoDtos.add(
                        new FriendsPhotoDto(
                                photo.toDto(),
                                friend.getProfileImage().getValue(),
                                friend.getNickname().getValue()
                        )
                );
            });
        });

        return new FriendsPhotosDto(friendsPhotoDtos);
    }
}

