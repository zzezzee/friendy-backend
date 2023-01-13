package com.zzezze.friendy.applications;

import com.zzezze.friendy.models.Invitation;
import com.zzezze.friendy.models.value_objects.Username;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationEventPublisher;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class AcceptInvitationServiceTest {
    DeleteReceivedInvitationService deleteReceivedInvitationService;
    AcceptInvitationService acceptInvitationService;
    ApplicationEventPublisher applicationEventPublisher;

    @BeforeEach
    void setup() {
        deleteReceivedInvitationService = mock(DeleteReceivedInvitationService.class);
        applicationEventPublisher = mock(ApplicationEventPublisher.class);
        acceptInvitationService = new AcceptInvitationService(deleteReceivedInvitationService, applicationEventPublisher);
    }

    @Test
    void refuse() {
        Username username1 = new Username("test2");

        String message = acceptInvitationService.accept(username1, 1L);

        verify(deleteReceivedInvitationService).delete(username1, 1L);

        assertThat(message).isEqualTo("Accept invitation success");
    }
}
