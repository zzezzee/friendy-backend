package com.zzezze.friendy.events;

import com.zzezze.friendy.applications.CreateRelationshipService;
import com.zzezze.friendy.exceptions.CancelInvitationFailed;
import jakarta.transaction.Transactional;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class InvitationAcceptEventHandler {
    private final CreateRelationshipService createRelationshipService;

    public InvitationAcceptEventHandler(CreateRelationshipService createRelationshipService) {
        this.createRelationshipService = createRelationshipService;
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void createRelationship(InvitationAcceptedEvent event){
        createRelationshipService.create(event.getInvitation());
    }
}
