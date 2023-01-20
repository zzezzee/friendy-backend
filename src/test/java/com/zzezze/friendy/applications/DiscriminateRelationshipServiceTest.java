package com.zzezze.friendy.applications;

import com.zzezze.friendy.models.Invitation;
import com.zzezze.friendy.models.Relationship;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.InvitationRepository;
import com.zzezze.friendy.repositories.RelationshipRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class DiscriminateRelationshipServiceTest {
    RelationshipRepository relationshipRepository;
    DiscriminateRelationshipService discriminateRelationshipService;
    InvitationRepository invitationRepository;

    @BeforeEach
    void setup() {
        relationshipRepository = mock(RelationshipRepository.class);
        invitationRepository = mock(InvitationRepository.class);
        discriminateRelationshipService = new DiscriminateRelationshipService(relationshipRepository, invitationRepository);
    }

    @Test
    void discriminateMe() {
        User visitor = User.fake();
        User owner = User.fake();

        String result = discriminateRelationshipService.discriminate(visitor, owner);

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

        String result = discriminateRelationshipService.discriminate(visitor, owner);

        assertThat(result).isEqualTo("friend");
    }

    @Test
    void discriminateApplier() {
        Username username1 = new Username("username1");
        Username username2 = new Username("username2");
        Username username3 = new Username("username3");

        User visitor = User.fake(username1);
        User owner = User.fake(username2);

        given(invitationRepository.findAllBySender(username2))
                .willReturn(List.of(new Invitation(username2, username1)));

        given(invitationRepository.findAllByReceiver(username2))
                .willReturn(List.of(new Invitation(username3, username2)));

        String result = discriminateRelationshipService.discriminate(visitor, owner);

        assertThat(result).isEqualTo("applier");
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

        String result = discriminateRelationshipService.discriminate(visitor, owner);

        assertThat(result).isEqualTo("stranger");
    }

}
