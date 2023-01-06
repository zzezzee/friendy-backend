package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.GuestBooksDto;
import com.zzezze.friendy.models.GuestBook;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.repositories.GuestBookRepository;
import com.zzezze.friendy.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

class GetGuestBooksServiceTest {
    GetGuestBooksService getGuestBookService;
    GuestBookRepository guestBookRepository;
    UserRepository userRepository;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        guestBookRepository = mock(GuestBookRepository.class);
        getGuestBookService = new GetGuestBooksService(guestBookRepository, userRepository);
    }

    @Test
    void list() {
        Nickname nickname = new Nickname("zzezze");

         User user = User.fake();

        given(userRepository.findByNickname(nickname))
                .willReturn(Optional.of(user));

        GuestBook guestBook = GuestBook.fake(user.getUsername());

        given(guestBookRepository.findAllByUsername(user.getUsername()))
                .willReturn(List.of(guestBook));

        given(userRepository.findByUsername(guestBook.getWriter()))
                .willReturn(Optional.of(User.fake()));

        GuestBooksDto guestBooksDto = getGuestBookService.list(nickname);

        assertThat(guestBooksDto.getGuestBooks().size()).isEqualTo(1);
    }
}
