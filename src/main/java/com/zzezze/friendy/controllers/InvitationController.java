package com.zzezze.friendy.controllers;

import com.zzezze.friendy.applications.CancelInvitationService;
import com.zzezze.friendy.applications.GetInvitationsService;
import com.zzezze.friendy.dtos.InvitationsDto;
import com.zzezze.friendy.models.value_objects.Username;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("invitations")
public class InvitationController {
    private final GetInvitationsService getInvitationsService;
    private final CancelInvitationService cancelInvitationService;

    public InvitationController(GetInvitationsService getInvitationsService, CancelInvitationService cancelInvitationService) {
        this.getInvitationsService = getInvitationsService;
        this.cancelInvitationService = cancelInvitationService;
    }

    @GetMapping
    public InvitationsDto list(
            @RequestAttribute("username") Username username
    ) {
        InvitationsDto invitationsDto = getInvitationsService.list(username);

        return invitationsDto;
    }

    @DeleteMapping("{id}")
    public String delete(
            @RequestAttribute("username") Username username,
            @PathVariable Long id,
            @RequestParam String type
    ) {
        if (type.equals("cancel")) {
            return cancelInvitationService.cancel(username, id);
        }

        return "delete failed";
    }
}
