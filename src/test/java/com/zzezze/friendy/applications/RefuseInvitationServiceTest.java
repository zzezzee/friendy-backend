package com.zzezze.friendy.applications;

import com.zzezze.friendy.models.Invitation;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.InvitationRepository;
import com.zzezze.friendy.repositories.UserRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class RefuseInvitationServiceTest {
    RefuseInvitationService refuseInvitationService;
    UserRepository userRepository;
    InvitationRepository invitationRepository;

    @BeforeEach
    void setup() {
        invitationRepository = mock(InvitationRepository.class);
        userRepository = mock(UserRepository.class);
        refuseInvitationService = new RefuseInvitationService(invitationRepository, userRepository);
    }

    @Test
    void refuse() {
        Username username1 = new Username("test2");
        Username username2 = new Username("test1");

        given(userRepository.findByUsername(username1))
                .willReturn(Optional.of(User.fake(username1)));

        given(userRepository.findById(1L))
                .willReturn(Optional.of(User.fake(username2)));

        given(invitationRepository.findBySenderAndReceiver(any(), any()))
                .willReturn(Optional.of(Invitation.fake()));

        String message = refuseInvitationService.refuse(username1, 1L);

        assertThat(message).isEqualTo("Refuse invitation success");
    }
}
