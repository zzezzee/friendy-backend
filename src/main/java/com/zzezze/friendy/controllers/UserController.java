package com.zzezze.friendy.controllers;

import com.zzezze.friendy.applications.GetUserService;
import com.zzezze.friendy.dtos.UserDto;
import com.zzezze.friendy.models.Username;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private GetUserService getUserService;

    public UserController(GetUserService getUserService) {
        this.getUserService = getUserService;
    }

    @GetMapping("/me")
    public UserDto user(
            @RequestAttribute("username") Username username
    ) {
        UserDto userDto = getUserService.detail(username);

        return userDto;
    }
}
