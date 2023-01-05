package com.zzezze.friendy.controllers;

import com.zzezze.friendy.applications.DeleteGuestBookService;
import com.zzezze.friendy.applications.GetGuestBookService;
import com.zzezze.friendy.applications.GetGuestBooksService;
import com.zzezze.friendy.dtos.GuestBookDeleteResponseDto;
import com.zzezze.friendy.dtos.GuestBookDto;
import com.zzezze.friendy.dtos.GuestBooksDto;
import com.zzezze.friendy.dtos.PhotoDeleteResponseDto;
import com.zzezze.friendy.models.GuestBook;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(GuestBookController.class)
class GuestBookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetGuestBooksService getGuestBooksService;

    @MockBean
    private GetGuestBookService getGuestBookService;

    @MockBean
    private DeleteGuestBookService deleteGuestBookService;

    @SpyBean
    private JwtUtil jwtUtil;

    @Test
    void list() throws Exception {
        Username username = new Username("test");
        Nickname nickname = new Nickname("zzezze");

        given(getGuestBooksService.list(nickname))
                .willReturn(new GuestBooksDto(List.of(GuestBook.fake(username).toDto())));

        mockMvc.perform(MockMvcRequestBuilders.get("/guest-books?nickname=zzezze"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"guestBooks\"")
                ));
    }

    @Test
    void guestBook() throws Exception {
        Username username = new Username("test");
        Nickname nickname = new Nickname("zzezze");

        given(getGuestBookService.guestBook(1L))
                .willReturn(GuestBook.fake(username).toDto());

        mockMvc.perform(MockMvcRequestBuilders.get("/guest-books/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"profileImage\"")
                ));
    }

    @Test
    void delete() throws Exception {
        Username username = new Username("test");

        given(deleteGuestBookService.delete(username, 1L))
                .willReturn(new GuestBookDeleteResponseDto(1L));

        String token = jwtUtil.encode(username.getValue());

        mockMvc.perform(MockMvcRequestBuilders.delete("/guest-books/1")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(content().string(
                        containsString("\"id\"")
                ));
    }
}
