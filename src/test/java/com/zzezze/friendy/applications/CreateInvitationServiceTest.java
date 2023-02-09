package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.UserDto;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.InvitationRepository;
import com.zzezze.friendy.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateInvitationServiceTest {
    InvitationRepository invitationRepository;
    UserRepository userRepository;
    CreateInvitationService createInvitationService;

    @BeforeEach
    void setup() {
        invitationRepository = mock(InvitationRepository.class);
        userRepository = mock(UserRepository.class);
        createInvitationService = new CreateInvitationService(invitationRepository, userRepository, notificationService);
    }

    @Test
    void create() {
        Username username = new Username("zzezze");
        Nickname nickname = new Nickname("jenna");

        given(userRepository.findByNickname(nickname))
                .willReturn(Optional.of(User.fake()));

        UserDto userDto = createInvitationService.create(username, nickname);

        verify(invitationRepository).save(any());
    }
}
