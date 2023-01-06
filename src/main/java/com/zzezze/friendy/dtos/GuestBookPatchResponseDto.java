package com.zzezze.friendy.dtos;

public class GuestBookPatchResponseDto {
    private Long id;

    public GuestBookPatchResponseDto() {
    }

    public GuestBookPatchResponseDto(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
