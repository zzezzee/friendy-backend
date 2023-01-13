package com.zzezze.friendy.events;

import com.zzezze.friendy.models.Invitation;

public class InvitationAcceptedEvent {
    private Invitation invitation;

    public InvitationAcceptedEvent(Invitation invitation) {
        this.invitation = invitation;
    }

    public static InvitationAcceptedEvent of(Invitation invitation) {
        return new InvitationAcceptedEvent(invitation);
    }

    public Invitation getInvitation() {
        return invitation;
    }
}
