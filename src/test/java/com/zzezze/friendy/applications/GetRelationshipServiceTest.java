package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.UsersDto;
import com.zzezze.friendy.models.Relationship;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.RelationshipRepository;
import com.zzezze.friendy.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetRelationshipServiceTest {
    GetRelationshipService getRelationshipService;
    RelationshipRepository relationshipRepository;
    UserRepository userRepository;

    @BeforeEach
    void setup() {
        relationshipRepository = mock(RelationshipRepository.class);
        userRepository = mock(UserRepository.class);
        getRelationshipService = new GetRelationshipService(userRepository, relationshipRepository);
    }

    @Test
    void list() {
        Username username1 = new Username("username1");
        Username username2 = new Username("username2");
        Username username3 = new Username("username3");

        given(userRepository.findByNickname(any()))
                .willReturn(Optional.of(User.fake(username1)));

        given(relationshipRepository.findAllBySender(username1))
                .willReturn(List.of(new Relationship(username1, username2)));

        given(relationshipRepository.findAllByReceiver(username1))
                .willReturn(List.of(new Relationship(username3, username1)));

        given(userRepository.findByUsername(username2))
                .willReturn(Optional.of(User.fake(username2)));

        given(userRepository.findByUsername(username3))
                .willReturn(Optional.of(User.fake(username3)));

        UsersDto usersDto = getRelationshipService.list(any());

        assertThat(usersDto.getUsers()).hasSize(2);
    }
}
