package com.zzezze.friendy.dtos;

public class UserRelationShipDto {
    private String nickname;
    private String relationship;

    public UserRelationShipDto() {
    }

    public UserRelationShipDto(String nickname, String relationship) {
        this.nickname = nickname;
        this.relationship = relationship;
    }

    public String getNickname() {
        return nickname;
    }

    public String getRelationship() {
        return relationship;
    }

    public static UserRelationShipDto fake() {
        return new UserRelationShipDto("zzezze", "me");
    }
}
