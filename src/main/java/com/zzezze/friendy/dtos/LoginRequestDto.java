package com.zzezze.friendy.dtos;

import jakarta.validation.constraints.NotBlank;

public class LoginRequestDto {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public LoginRequestDto() {
    }

    public LoginRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
