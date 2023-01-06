package com.zzezze.friendy.controllers;

import com.zzezze.friendy.applications.CreateGuestBookService;
import com.zzezze.friendy.applications.DeleteGuestBookService;
import com.zzezze.friendy.applications.GetGuestBookService;
import com.zzezze.friendy.applications.GetGuestBooksService;
import com.zzezze.friendy.dtos.GuestBookDeleteResponseDto;
import com.zzezze.friendy.dtos.GuestBooksDto;
import com.zzezze.friendy.models.GuestBook;
import com.zzezze.friendy.models.Photo;
import com.zzezze.friendy.models.value_objects.Content;
import com.zzezze.friendy.models.value_objects.Explanation;
import com.zzezze.friendy.models.value_objects.Image;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.value_objects.ProfileImage;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.parameters.P;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
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
    private CreateGuestBookService createGuestBookService;

    @MockBean
    private DeleteGuestBookService deleteGuestBookService;

    @SpyBean
    private JwtUtil jwtUtil;

    @Test
    void list() throws Exception {
        Username username = new Username("test");
        Nickname nickname = new Nickname("zzezze");
        ProfileImage profileImage = new ProfileImage("image_address");

        given(getGuestBooksService.list(nickname))
                .willReturn(new GuestBooksDto(List.of(GuestBook.fake(username).toDto(nickname, profileImage))));

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
        ProfileImage profileImage = new ProfileImage("image_address");

        given(getGuestBookService.guestBook(1L))
                .willReturn(GuestBook.fake(username).toDto(nickname, profileImage));

        mockMvc.perform(MockMvcRequestBuilders.get("/guest-books/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"nickname\"")
                ));
    }

    @Test
    void create() throws Exception {
        Username username = new Username("test");
        Content content = new Content("방명록 내용");
        Nickname miniHomepageOwner = new Nickname("미니홈피 주인 닉네임");
        ProfileImage profileImage = new ProfileImage("image_address");

        given(createGuestBookService.create(username, content, miniHomepageOwner))
                .willReturn(GuestBook.fake().toDto(miniHomepageOwner, profileImage));

        String token = jwtUtil.encode(username.getValue());

        mockMvc.perform(MockMvcRequestBuilders.post("/guest-books")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"content\":\"방명록 내용\"," +
                                "\"nickname\":\"미니홈피 주인 닉네임\"" +
                                "}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(
                        containsString("\"writer\"")
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
