package com.zzezze.friendy.applications;

import com.zzezze.friendy.models.Invitation;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.InvitationRepository;
import com.zzezze.friendy.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CancelInvitationServiceTest {
    CancelInvitationService deleteInvitationService;
    InvitationRepository invitationRepository;
    UserRepository userRepository;

    @BeforeEach
    void setup() {
        invitationRepository = mock(InvitationRepository.class);
        userRepository = mock(UserRepository.class);
        deleteInvitationService = new CancelInvitationService(invitationRepository, userRepository);
    }

    @Test
    void cancel() {
        Username username1 = new Username("test1");
        Username username2 = new Username("test2");


        given(userRepository.findByUsername(any()))
                .willReturn(Optional.of(User.fake(username1)));

        given(userRepository.findById(any()))
                .willReturn(Optional.of(User.fake(username2)));

        given(invitationRepository.findBySenderAndReceiver(username1, username2))
                .willReturn(Optional.of(Invitation.fake()));

        String message = deleteInvitationService.cancel(username1, 1L);

        verify(invitationRepository).deleteByReceiver(username2);

        assertThat(message).isEqualTo("Cancel invitation success");
    }
}
