package com.zzezze.friendy.dtos;

import java.util.List;

public class GuestBooksDto {
    List<GuestBookDto> guestBooks;

    public GuestBooksDto(List<GuestBookDto> guestBooks) {
        this.guestBooks = guestBooks;
    }

    public List<GuestBookDto> getGuestBooks() {
        return guestBooks;
    }
}
