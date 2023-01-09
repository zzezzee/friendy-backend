package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.UserDto;
import com.zzezze.friendy.dtos.UserRelationShipDto;
import com.zzezze.friendy.models.RelationShip;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetUserServiceTest {
    GetUserService getUserService;
    UserRepository userRepository;
    RelationShip relationShip;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        relationShip = new RelationShip();
        getUserService = new GetUserService(userRepository, relationShip);
    }

    @Test
    void detail() {
        Username username = new Username("test");
        Nickname nickname = new Nickname("zzezze");

        given(userRepository.findByUsername(username))
                .willReturn(Optional.of(User.fake()));

        UserRelationShipDto userDto = getUserService.detail(username, nickname);

        assertThat(userDto.getNickname()).isEqualTo("zzezze");
    }
}
