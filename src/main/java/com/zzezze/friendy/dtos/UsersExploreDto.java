package com.zzezze.friendy.dtos;

import java.util.List;

public class UsersExploreDto {
    private List<UserExploreDto> users;

    public UsersExploreDto(List<UserExploreDto> userExploreDtos) {
        this.users = userExploreDtos;
    }

    public List<UserExploreDto> getUsers() {
        return users;
    }
}
