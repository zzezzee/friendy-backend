package com.zzezze.friendy.controllers;

import com.zzezze.friendy.applications.GetPhotosService;
import com.zzezze.friendy.dtos.PhotosDto;
import com.zzezze.friendy.models.Nickname;
import com.zzezze.friendy.models.Photo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PhotoController.class)
class PhotoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetPhotosService getPhotosService;

    @Test
    void miniHomepage() throws Exception {
        Nickname nickname = new Nickname("zzezze");
        given(getPhotosService.list(nickname))
                .willReturn(new PhotosDto(List.of(Photo.fake().toDto())));

        mockMvc.perform(MockMvcRequestBuilders.get("/photo-books?nickname=zzezze"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"photos\"")
                ));
    }

}
