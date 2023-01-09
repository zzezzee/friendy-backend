package com.zzezze.friendy.dtos;

import java.util.List;

public class UsersDto {
    List<UserDto> users;

    public UsersDto(List<UserDto> users) {
        this.users = users;
    }

    public List<UserDto> getUsers() {
        return users;
    }
}
