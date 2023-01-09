package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.UsersDto;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetUsersServiceTest {
    UserRepository userRepository;
    GetUsersService getUsersService;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        getUsersService = new GetUsersService(userRepository);
    }

    @Test
    void list() {
        given(userRepository.findAll())
                .willReturn(List.of(User.fake()));

        UsersDto usersDto = getUsersService.list();

        assertThat(usersDto.getUsers()).hasSize(1);
    }
}
