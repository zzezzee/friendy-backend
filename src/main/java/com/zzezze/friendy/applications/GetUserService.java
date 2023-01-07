package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.UserDto;
import com.zzezze.friendy.exceptions.UserNotFound;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class GetUserService {
    private final UserRepository userRepository;

    public GetUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto detail(Username username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(UserNotFound::new);

        return user.toDto();
    }
}
