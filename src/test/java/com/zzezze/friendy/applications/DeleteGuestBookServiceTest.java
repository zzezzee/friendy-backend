package com.zzezze.friendy.applications;

import com.zzezze.friendy.models.GuestBook;
import com.zzezze.friendy.models.Photo;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.GuestBookRepository;
import com.zzezze.friendy.repositories.PhotoRepository;
import com.zzezze.friendy.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class DeleteGuestBookServiceTest {
    GuestBookRepository guestBookRepository;
    UserRepository userRepository;
    DeleteGuestBookService deleteGuestBookService;

    @BeforeEach
    void setup() {
        guestBookRepository = mock(GuestBookRepository.class);
        userRepository = mock(UserRepository.class);
        deleteGuestBookService = new DeleteGuestBookService(guestBookRepository, userRepository);
    }

    @Test
    void delete() {
        Username username = new Username("test");

        given(userRepository.findByUsername(username))
                .willReturn(Optional.of(User.fake()));

        given(guestBookRepository.findById(1L))
                .willReturn(Optional.of(GuestBook.fake()));

        deleteGuestBookService.delete(username, 1L);

        verify(guestBookRepository).findById(1L);
        verify(guestBookRepository).deleteById(1L);
    }
}
