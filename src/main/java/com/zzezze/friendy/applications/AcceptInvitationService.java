package com.zzezze.friendy.applications;

import com.zzezze.friendy.events.InvitationAcceptedEvent;
import com.zzezze.friendy.models.Invitation;
import com.zzezze.friendy.models.value_objects.Username;
import jakarta.transaction.Transactional;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class AcceptInvitationService {
    private final DeleteReceivedInvitationService deleteReceivedInvitationService;
    private final ApplicationEventPublisher publisher;

    public AcceptInvitationService(DeleteReceivedInvitationService deleteReceivedInvitationService, ApplicationEventPublisher publisher) {
        this.deleteReceivedInvitationService = deleteReceivedInvitationService;
        this.publisher = publisher;
    }

    public String accept(Username receiver, Long senderId) {
        Invitation invitation = deleteReceivedInvitationService.delete(receiver, senderId);

        publisher.publishEvent(InvitationAcceptedEvent.of(invitation));

        return "Accept invitation success";
    }
}
