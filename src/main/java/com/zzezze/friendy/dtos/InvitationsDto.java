package com.zzezze.friendy.dtos;

import java.util.List;

public class InvitationsDto {
    List<UserDto> invitationsReceived;
    List<UserDto> invitationsSent;

    public InvitationsDto(List<UserDto> invitationsReceived, List<UserDto> invitationsSent) {
        this.invitationsReceived = invitationsReceived;
        this.invitationsSent = invitationsSent;
    }

    public List<UserDto> getInvitationsReceived() {
        return invitationsReceived;
    }

    public List<UserDto> getInvitationsSent() {
        return invitationsSent;
    }
}
