package com.zzezze.friendy.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zzezze.friendy.applications.AcceptInvitationService;
import com.zzezze.friendy.applications.CancelInvitationService;
import com.zzezze.friendy.applications.CreateInvitationService;
import com.zzezze.friendy.applications.GetInvitationsService;
import com.zzezze.friendy.applications.RefuseInvitationService;
import com.zzezze.friendy.dtos.InvitationsDto;
import com.zzezze.friendy.dtos.UserDto;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.value_objects.Username;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("invitations")
public class InvitationController {
    private final GetInvitationsService getInvitationsService;
    private final CancelInvitationService cancelInvitationService;
    private final RefuseInvitationService refuseInvitationService;
    private final AcceptInvitationService acceptInvitationService;
    private CreateInvitationService createInvitationService;

    public InvitationController(GetInvitationsService getInvitationsService, CancelInvitationService cancelInvitationService, RefuseInvitationService refuseInvitationService, AcceptInvitationService acceptInvitationService, CreateInvitationService createInvitationService) {
        this.getInvitationsService = getInvitationsService;
        this.cancelInvitationService = cancelInvitationService;
        this.refuseInvitationService = refuseInvitationService;
        this.acceptInvitationService = acceptInvitationService;
        this.createInvitationService = createInvitationService;
    }

    @GetMapping
    public InvitationsDto list(
            @RequestAttribute("username") Username username
    ) {
        InvitationsDto invitationsDto = getInvitationsService.list(username);

        return invitationsDto;
    }

    @PostMapping
    public UserDto create(
            @RequestAttribute("username") Username username,
            @RequestParam Nickname nickname
            ) throws JsonProcessingException {
        return createInvitationService.create(username, nickname);
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
        if (type.equals("refuse")) {
            return refuseInvitationService.refuse(username, id);
        }
        if (type.equals("accept")) {
            return acceptInvitationService.accept(username, id);
        }

        return "type undefined";
    }
}
