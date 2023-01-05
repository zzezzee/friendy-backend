package com.zzezze.friendy.controllers;

import com.zzezze.friendy.applications.DeleteGuestBookService;
import com.zzezze.friendy.applications.GetGuestBooksService;
import com.zzezze.friendy.applications.GetGuestBookService;
import com.zzezze.friendy.dtos.GuestBookDeleteResponseDto;
import com.zzezze.friendy.dtos.GuestBookDto;
import com.zzezze.friendy.dtos.GuestBooksDto;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.value_objects.Username;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("guest-books")
public class GuestBookController {
    private final GetGuestBooksService getGuestBooksService;
    private final GetGuestBookService getGuestBookService;
    private final DeleteGuestBookService deleteGuestBookService;

    public GuestBookController(GetGuestBooksService getGuestBooksService, GetGuestBookService getGuestBookService, DeleteGuestBookService deleteGuestBookService) {
        this.getGuestBooksService = getGuestBooksService;
        this.getGuestBookService = getGuestBookService;
        this.deleteGuestBookService = deleteGuestBookService;
    }

    @GetMapping
    public GuestBooksDto list(
            @RequestParam Nickname nickname
    ) {
        GuestBooksDto guestBooksDto = getGuestBooksService.list(nickname);

        return guestBooksDto;
    }

    @GetMapping("{id}")
    public GuestBookDto guestBook(
            @PathVariable Long id
    ) {
        GuestBookDto guestBookDto = getGuestBookService.guestBook(id);

        return guestBookDto;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public GuestBookDeleteResponseDto delete(
            @RequestAttribute("username") Username username,
            @PathVariable Long id
    ) {
        GuestBookDeleteResponseDto guestBookDeleteResponseDto= deleteGuestBookService.delete(username, id);

        return guestBookDeleteResponseDto;
    }
}
