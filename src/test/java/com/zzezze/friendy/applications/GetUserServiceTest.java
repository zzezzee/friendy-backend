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
    RelationshipRepository relationshipRepository;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        relationshipRepository = mock(RelationshipRepository.class);
        getUserService = new GetUserService(userRepository, relationshipRepository);
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

    @Test
    void discriminateMe() {
        User visitor = User.fake();
        User owner = User.fake();

        String result = getUserService.discriminate(visitor, owner);

        assertThat(result).isEqualTo("me");
    }

    @Test
    void discriminateFriend() {
        Username username1 = new Username("username1");
        Username username2 = new Username("username2");

        User visitor = User.fake(username1);
        User owner = User.fake(username2);

        given(relationshipRepository.findAllBySenderOrReceiver(username1, username1))
                .willReturn(List.of(new Relationship(1L, username1, username2)));

        given(relationshipRepository.findAllBySenderOrReceiver(username2, username2))
                .willReturn(List.of(new Relationship(1L, username1, username2)));

        String result = getUserService.discriminate(visitor, owner);

        assertThat(result).isEqualTo("friend");
    }

    @Test
    void discriminateStranger() {
        Username username1 = new Username("username1");
        Username username2 = new Username("username2");
        Username username3 = new Username("username3");

        User visitor = User.fake(username1);
        User owner = User.fake(username2);

        given(relationshipRepository.findAllBySenderOrReceiver(username1, username1))
                .willReturn(List.of(new Relationship(1L, username1, username3)));

        given(relationshipRepository.findAllBySenderOrReceiver(username2, username2))
                .willReturn(List.of(new Relationship(2L, username2, username3)));

        String result = getUserService.discriminate(visitor, owner);

        assertThat(result).isEqualTo("stranger");
    }
}


