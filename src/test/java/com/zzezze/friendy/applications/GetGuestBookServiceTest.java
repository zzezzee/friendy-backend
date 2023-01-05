package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.GuestBookDto;
import com.zzezze.friendy.dtos.GuestBooksDto;
import com.zzezze.friendy.models.GuestBook;
import com.zzezze.friendy.repositories.GuestBookRepository;
import com.zzezze.friendy.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetGuestBookServiceTest {
    GetGuestBookService getGuestBookService;
    GuestBookRepository guestBookRepository;

    @BeforeEach
    void setup() {
        guestBookRepository = mock(GuestBookRepository.class);
        getGuestBookService = new GetGuestBookService(guestBookRepository);
    }

    @Test
    void guestBook() {
        given(guestBookRepository.findById(1L))
                .willReturn(Optional.of(GuestBook.fake()));

        GuestBookDto guestBookDto = getGuestBookService.guestBook(1L);

        assertThat(guestBookDto.getWriter()).isEqualTo("writer");
    }
}
