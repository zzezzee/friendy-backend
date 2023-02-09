package com.zzezze.friendy.events;

import com.zzezze.friendy.applications.CreateRelationshipService;
import org.junit.jupiter.api.BeforeEach;

import static org.mockito.Mockito.mock;

class InvitationAcceptEventHandlerTest {
    CreateRelationshipService createRelationshipService;
    InvitationAcceptEventHandler invitationAcceptEventHandler;

    @BeforeEach
    void setup() {
        createRelationshipService = mock(CreateRelationshipService.class);
        invitationAcceptEventHandler = new InvitationAcceptEventHandler(createRelationshipService, acceptInvitationNotificationService);
    }
}
