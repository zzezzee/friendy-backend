package com.zzezze.friendy.controllers;

import com.zzezze.friendy.applications.CreatePhotoService;
import com.zzezze.friendy.applications.DeletePhotoService;
import com.zzezze.friendy.applications.GetPhotosService;
import com.zzezze.friendy.dtos.PhotoDeleteResponseDto;
import com.zzezze.friendy.dtos.PhotoDto;
import com.zzezze.friendy.dtos.PhotosDto;
import com.zzezze.friendy.models.Explanation;
import com.zzezze.friendy.models.Image;
import com.zzezze.friendy.models.Nickname;
import com.zzezze.friendy.models.Photo;
import com.zzezze.friendy.models.Username;
import com.zzezze.friendy.utils.JwtUtil;
import com.zzezze.friendy.utils.S3Uploader;
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

@WebMvcTest(PhotoController.class)
class PhotoControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetPhotosService getPhotosService;

    @MockBean
    private CreatePhotoService createPhotoService;

    @MockBean
    private DeletePhotoService deletePhotoService;

    @SpyBean
    private JwtUtil jwtUtil;

    @MockBean
    private S3Uploader s3Uploader;

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

    @Test
    void create() throws Exception {
        Username username = new Username("test");
        Image image = new Image("image_address");
        Explanation explanation = new Explanation("이미지 설명");

        given(createPhotoService.create(username, image, explanation))
                .willReturn(Photo.fake().toDto());

        String token = jwtUtil.encode(username.getValue());

        mockMvc.perform(MockMvcRequestBuilders.post("/photo-books")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"image\":\"image_address\"," +
                                "\"explanation\":\"이미지 설명\"" +
                                "}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(
                        containsString("\"image\"")
                ));
    }

    @Test
    void delete() throws Exception {
        Username username = new Username("test");

        given(deletePhotoService.delete(username, 1L))
                .willReturn(new PhotoDeleteResponseDto(1L));

        String token = jwtUtil.encode(username.getValue());

        mockMvc.perform(MockMvcRequestBuilders.delete("/photo-books/1")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(content().string(
                        containsString("\"id\"")
                ));
    }
}
