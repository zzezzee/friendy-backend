package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.GuestBookPatchResponseDto;
import com.zzezze.friendy.models.GuestBook;
import com.zzezze.friendy.models.value_objects.Content;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.GuestBookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PatchGuestBookServiceTest {
    GuestBookRepository guestBookRepository;
    PatchGuestBookService patchGuestBookService;

    @BeforeEach
    void setup() {
        guestBookRepository = mock(GuestBookRepository.class);
        patchGuestBookService = new PatchGuestBookService(guestBookRepository);
    }

    @Test
    void patch() {
        Username username = new Username("test2");
        Long id = 1L;
        Content content = new Content("수정된 방명록 내용");

        GuestBook guestBook = GuestBook.fake();

        given(guestBookRepository.findById(id))
                .willReturn(Optional.of(guestBook));

        GuestBookPatchResponseDto guestBookPatchResponseDto = patchGuestBookService.patch(username, id, content);

        assertThat(guestBookPatchResponseDto.getId()).isEqualTo(1L);
    }
}
