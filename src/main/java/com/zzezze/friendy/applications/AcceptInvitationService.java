package com.zzezze.friendy.applications;

import com.zzezze.friendy.models.Invitation;
import com.zzezze.friendy.models.value_objects.Username;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AcceptInvitationService {
    private final DeleteReceivedInvitationService deleteReceivedInvitationService;
    private final CreateRelationshipService createRelationshipService;

    public AcceptInvitationService(DeleteReceivedInvitationService deleteReceivedInvitationService, CreateRelationshipService createRelationshipService) {
        this.deleteReceivedInvitationService = deleteReceivedInvitationService;
        this.createRelationshipService = createRelationshipService;
    }

    public String accept(Username receiver, Long senderId) {
        Invitation invitation = deleteReceivedInvitationService.delete(receiver, senderId);

        createRelationshipService.create(invitation);

        return "accept invitation success";
    }
}
