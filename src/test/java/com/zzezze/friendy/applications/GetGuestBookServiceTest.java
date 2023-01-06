package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.GuestBookDto;
import com.zzezze.friendy.models.GuestBook;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.repositories.GuestBookRepository;
import com.zzezze.friendy.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetGuestBookServiceTest {
    GetGuestBookService getGuestBookService;
    GuestBookRepository guestBookRepository;
    UserRepository userRepository;

    @BeforeEach
    void setup() {
        guestBookRepository = mock(GuestBookRepository.class);
        userRepository = mock(UserRepository.class);
        getGuestBookService = new GetGuestBookService(guestBookRepository, userRepository);
    }

    @Test
    void guestBook() {
        GuestBook guestBook = GuestBook.fake();

        given(guestBookRepository.findById(1L))
                .willReturn(Optional.of(guestBook));

        given(userRepository.findByUsername(guestBook.getWriter()))
                .willReturn(Optional.of(User.fake()));

        GuestBookDto guestBookDto = getGuestBookService.guestBook(1L);

        assertThat(guestBookDto.getNickname()).isEqualTo("zzezze");
    }
}
