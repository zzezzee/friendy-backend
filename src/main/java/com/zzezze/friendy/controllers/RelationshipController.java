package com.zzezze.friendy.controllers;

import com.zzezze.friendy.applications.GetRelationshipService;
import com.zzezze.friendy.dtos.UsersDto;
import com.zzezze.friendy.models.value_objects.Nickname;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("relationship")
public class RelationshipController {
    private final GetRelationshipService getRelationshipsService;

    public RelationshipController(GetRelationshipService getRelationshipsService) {
        this.getRelationshipsService = getRelationshipsService;
    }


    @GetMapping
    public UsersDto list(
            @RequestParam Nickname nickname
    ) {
        UsersDto usersDto = getRelationshipsService.list(nickname);

        return usersDto;
    }
}
