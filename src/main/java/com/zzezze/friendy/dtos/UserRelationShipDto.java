package com.zzezze.friendy.dtos;

public class UserRelationShipDto {
    private String nickname;
    private String relationShip;

    public UserRelationShipDto() {
    }

    public UserRelationShipDto(String nickname, String relationShip) {
        this.nickname = nickname;
        this.relationShip = relationShip;
    }

    public String getNickname() {
        return nickname;
    }

    public String getRelationShip() {
        return relationShip;
    }

    public static UserRelationShipDto fake() {
        return new UserRelationShipDto("zzezze", "me");
    }
}
