package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.GuestBookDto;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Content;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.GuestBookRepository;
import com.zzezze.friendy.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateGuestBookServiceTest {
    UserRepository userRepository;
    GuestBookRepository guestBookRepository;
    CreateGuestBookService createGuestBookService;

    @BeforeEach
    void setup() {
        userRepository = mock(UserRepository.class);
        guestBookRepository = mock(GuestBookRepository.class);
        createGuestBookService = new CreateGuestBookService(guestBookRepository, userRepository);
    }

    @Test
    void create() {
        Username username = new Username("test");
        Nickname nickname = new Nickname("zzezze");
        Content content = new Content("방명록 내용");

        given(userRepository.findByUsername(any()))
                .willReturn(Optional.of(User.fake()));

        given(userRepository.findByNickname(any()))
                .willReturn(Optional.of(User.fake()));

        GuestBookDto guestBookDto = createGuestBookService.create(username, content, nickname);

        verify(guestBookRepository).save(any());
    }
}
