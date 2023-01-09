package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.UserDto;
import com.zzezze.friendy.dtos.UsersDto;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GetUsersService {
    private final UserRepository userRepository;

    public GetUsersService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UsersDto list() {
        List<User> users = userRepository.findAll();

        List<UserDto> userDtos = users.stream()
                .map(User::toDto)
                .toList();

        return new UsersDto(userDtos);
    }
}
