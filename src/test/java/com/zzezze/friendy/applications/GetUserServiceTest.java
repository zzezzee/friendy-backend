package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.UserRelationShipDto;
import com.zzezze.friendy.models.Relationship;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.RelationshipRepository;
import com.zzezze.friendy.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetUserServiceTest {
    GetUserService getUserService;
    UserRepository userRepository;
    DiscriminateRelationshipService discriminateRelationshipService;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        discriminateRelationshipService = mock(DiscriminateRelationshipService.class);
        getUserService = new GetUserService(userRepository, discriminateRelationshipService);
    }

    @Test
    void detail() {
        Username username = new Username("test");
        Nickname nickname = new Nickname("zzezze");

        given(userRepository.findByUsername(username))
                .willReturn(Optional.of(User.fake()));

        given(userRepository.findByNickname(nickname))
                .willReturn(Optional.of(User.fake()));

        UserRelationShipDto userDto = getUserService.detail(username, nickname);

        assertThat(userDto.getNickname()).isEqualTo("zzezze");
    }
}


