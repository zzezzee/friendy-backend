package com.zzezze.friendy.dtos;

public class GuestBookDeleteResponseDto {
    private Long id;

    public GuestBookDeleteResponseDto() {
    }

    public GuestBookDeleteResponseDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
