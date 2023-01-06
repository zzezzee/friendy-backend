package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.GuestBookDto;
import com.zzezze.friendy.models.GuestBook;
import com.zzezze.friendy.models.MiniHomepage;
import com.zzezze.friendy.repositories.GuestBookRepository;
import com.zzezze.friendy.repositories.MiniHomepageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetGuestBookServiceTest {
    GetGuestBookService getGuestBookService;
    GuestBookRepository guestBookRepository;
    MiniHomepageRepository miniHomepageRepository;

    @BeforeEach
    void setup() {
        guestBookRepository = mock(GuestBookRepository.class);
        miniHomepageRepository = mock(MiniHomepageRepository.class);
        getGuestBookService = new GetGuestBookService(guestBookRepository, miniHomepageRepository);
    }

    @Test
    void guestBook() {
        GuestBook guestBook = GuestBook.fake();

        given(guestBookRepository.findById(1L))
                .willReturn(Optional.of(guestBook));

        given(miniHomepageRepository.findByUsername(guestBook.getUsername()))
                .willReturn(Optional.of(MiniHomepage.fake()));

        GuestBookDto guestBookDto = getGuestBookService.guestBook(1L);

        assertThat(guestBookDto.getNickname()).isEqualTo("zzezze");
    }
}
