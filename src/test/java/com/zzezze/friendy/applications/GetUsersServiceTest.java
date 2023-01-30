package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.UsersDto;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetUsersServiceTest {
    UserRepository userRepository;
    GetUsersService getUsersService;
    GetRelationshipService getRelationshipService;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        getRelationshipService = mock(GetRelationshipService.class);
        getUsersService = new GetUsersService(userRepository, getRelationshipService);
    }

    @Test
    void list() {
        given(userRepository.findAll())
                .willReturn(List.of(User.fake()));

//        UsersDto usersDto = getUsersService.list(username);

//        assertThat(usersDto.getUsers()).hasSize(1);
    }
}
