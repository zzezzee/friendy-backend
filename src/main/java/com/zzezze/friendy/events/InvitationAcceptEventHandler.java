package com.zzezze.friendy.events;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zzezze.friendy.applications.CreateRelationshipService;
import com.zzezze.friendy.applications.notifications.AcceptInvitationNotificationService;
import com.zzezze.friendy.models.Invitation;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class InvitationAcceptEventHandler {
    private final CreateRelationshipService createRelationshipService;
    private final AcceptInvitationNotificationService acceptInvitationNotificationService;

    public InvitationAcceptEventHandler(CreateRelationshipService createRelationshipService, AcceptInvitationNotificationService acceptInvitationNotificationService) {
        this.createRelationshipService = createRelationshipService;
        this.acceptInvitationNotificationService = acceptInvitationNotificationService;
    }

    @EventListener
    public void createRelationship(InvitationAcceptedEvent event){
        createRelationshipService.create(event.getInvitation());
    }

    @EventListener
    public void sendNotification(InvitationAcceptedEvent event) throws JsonProcessingException {
        Invitation invitation = event.getInvitation();

        acceptInvitationNotificationService.sendNotification(invitation.getSender(), invitation.getReceiver());
    }
}
