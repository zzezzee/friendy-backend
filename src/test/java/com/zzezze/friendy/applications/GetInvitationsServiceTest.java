package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.InvitationsDto;
import com.zzezze.friendy.dtos.UserDto;
import com.zzezze.friendy.models.Invitation;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.InvitationRepository;
import com.zzezze.friendy.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetInvitationsServiceTest {
    InvitationRepository invitationRepository;
    UserRepository userRepository;
    GetInvitationsService getInvitationsService;

    @BeforeEach
    void setup() {
        invitationRepository = mock(InvitationRepository.class);
        userRepository = mock(UserRepository.class);
        getInvitationsService = new GetInvitationsService(invitationRepository, userRepository);
    }

    @Test
    void list() {
        given(invitationRepository.findAllByReceiver(any()))
                .willReturn(List.of(Invitation.fake()));

        given(userRepository.findByUsername(new Username("test1")))
                .willReturn(Optional.of(User.fake()));

        given(invitationRepository.findAllBySender(any()))
                .willReturn(List.of(Invitation.fake()));

        given(userRepository.findByUsername(new Username("test2")))
                .willReturn(Optional.of(User.fake()));

        InvitationsDto invitationsDto = getInvitationsService.list(any());

        assertThat(invitationsDto.getInvitationsReceived()).hasSize(1);
        assertThat(invitationsDto.getInvitationsSent()).hasSize(1);
    }

    @Test
    void getUserReceivedDtos() {
        given(invitationRepository.findAllByReceiver(any()))
                .willReturn(List.of(Invitation.fake()));

        given(userRepository.findByUsername(new Username("test1")))
                .willReturn(Optional.of(User.fake()));

        List<UserDto> userReceivedDtos = getInvitationsService.getUserReceivedDtos(any());

        assertThat(userReceivedDtos).hasSize(1);
    }

    @Test
    void getUserSentDtos() {
        given(invitationRepository.findAllBySender(any()))
                .willReturn(List.of(Invitation.fake()));

        given(userRepository.findByUsername(new Username("test2")))
                .willReturn(Optional.of(User.fake()));

        List<UserDto> userSentDtos = getInvitationsService.getUserSentDtos(any());

        assertThat(userSentDtos).hasSize(1);
    }
}
