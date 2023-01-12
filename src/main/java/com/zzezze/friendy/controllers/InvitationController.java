package com.zzezze.friendy.controllers;

import com.zzezze.friendy.applications.GetInvitationsService;
import com.zzezze.friendy.dtos.InvitationsDto;
import com.zzezze.friendy.models.value_objects.Username;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("invitations")
public class InvitationController {
    private final GetInvitationsService getInvitesService;

    public InvitationController(GetInvitationsService getInvitesService) {
        this.getInvitesService = getInvitesService;
    }

    @GetMapping
    public InvitationsDto list(
            @RequestAttribute("username") Username username
    ) {
        InvitationsDto invitationsDto = getInvitesService.list(username);

        return invitationsDto;
    }
}
