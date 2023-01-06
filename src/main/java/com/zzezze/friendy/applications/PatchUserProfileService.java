package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.UserDto;
import com.zzezze.friendy.exceptions.UserNotFound;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Introduction;
import com.zzezze.friendy.models.value_objects.ProfileImage;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PatchUserProfileService {
    private final UserRepository userRepository;

    public PatchUserProfileService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto patch(Username username, ProfileImage profileImage, Introduction introduction) {
        User user = userRepository.findByUsername(username)
                        .orElseThrow(UserNotFound::new);

        user.changeProfile(profileImage, introduction);

        return user.toDto();
    }
}
