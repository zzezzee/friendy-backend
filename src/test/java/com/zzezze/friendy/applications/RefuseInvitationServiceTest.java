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
import static org.assertj.core.api.Assertions.in;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class RefuseInvitationServiceTest {
    DeleteReceivedInvitationService deleteReceivedInvitationService;
    RefuseInvitationService refuseInvitationService;

    @BeforeEach
    void setup() {
        deleteReceivedInvitationService = mock(DeleteReceivedInvitationService.class);
        refuseInvitationService = new RefuseInvitationService(deleteReceivedInvitationService);
    }

    @Test
    void refuse() {
        Username username1 = new Username("test2");

        String message = refuseInvitationService.refuse(username1, 1L);

        verify(deleteReceivedInvitationService).delete(username1, 1L);

        assertThat(message).isEqualTo("Refuse invitation success");
    }
}
