package com.zzezze.friendy.controllers;

import com.zzezze.friendy.applications.LoginService;
import com.zzezze.friendy.dtos.LoginRequestDto;
import com.zzezze.friendy.dtos.LoginResultDto;
import com.zzezze.friendy.models.Password;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.Username;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/session")
public class SessionController {
    private LoginService loginService;

    public SessionController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResultDto login(
            @Valid @RequestBody LoginRequestDto loginRequestDto
    ){
        Username username = new Username(loginRequestDto.getUsername());
        Password password = new Password(loginRequestDto.getPassword());

        LoginResultDto loginResultDto = loginService.login(username, password);

        return loginResultDto;
    }
}
