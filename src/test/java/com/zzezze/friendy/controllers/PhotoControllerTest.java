package com.zzezze.friendy.controllers;

import com.zzezze.friendy.applications.CreatePhotoService;
import com.zzezze.friendy.applications.DeletePhotoService;
import com.zzezze.friendy.applications.GetFriendsPhotosService;
import com.zzezze.friendy.applications.GetPhotoDetailService;
import com.zzezze.friendy.applications.GetPhotosService;
import com.zzezze.friendy.applications.PatchPhotoService;
import com.zzezze.friendy.dtos.CommentDto;
import com.zzezze.friendy.dtos.FriendsPhotoDto;
import com.zzezze.friendy.dtos.FriendsPhotosDto;
import com.zzezze.friendy.dtos.PhotoDeleteResponseDto;
import com.zzezze.friendy.dtos.PhotoDetailDto;
import com.zzezze.friendy.dtos.PhotosDto;
import com.zzezze.friendy.models.value_objects.Explanation;
import com.zzezze.friendy.models.value_objects.Image;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.Photo;
import com.zzezze.friendy.models.value_objects.Username;
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
import static org.mockito.ArgumentMatchers.any;
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
    private GetPhotoDetailService getPhotoDetailService;

    @MockBean
    private CreatePhotoService createPhotoService;

    @MockBean
    private PatchPhotoService patchPhotoService;

    @MockBean
    private DeletePhotoService deletePhotoService;

    @MockBean
    private GetFriendsPhotosService getFriendsPhotosService;

    @SpyBean
    private JwtUtil jwtUtil;

    @MockBean
    private S3Uploader s3Uploader;

    @Test
    void list() throws Exception {
        Nickname nickname = new Nickname("zzezze");
        given(getPhotosService.list(nickname))
                .willReturn(new PhotosDto(List.of(Photo.fake().toDto())));

        mockMvc.perform(MockMvcRequestBuilders.get("/photos?nickname=zzezze"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"photos\"")
                ));
    }

    @Test
    void detail() throws Exception {
        given(getPhotoDetailService.detail(1L))
                .willReturn(PhotoDetailDto.fake());

        mockMvc.perform(MockMvcRequestBuilders.get("/photos/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"photo\"")
                ))
                .andExpect(content().string(
                        containsString("\"likes\"")
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

        mockMvc.perform(MockMvcRequestBuilders.post("/photos")
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

        mockMvc.perform(MockMvcRequestBuilders.delete("/photos/1")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(content().string(
                        containsString("\"id\"")
                ));
    }

    @Test
    void patch() throws Exception {
        Username username = new Username("test");

        given(patchPhotoService.patch(any(), any(), any(), any()))
                .willReturn(Photo.fake().toDto());

        String token = jwtUtil.encode(username.getValue());

        mockMvc.perform(MockMvcRequestBuilders.patch("/photos/1")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"id\":\"1\"," +
                                "\"image\":\"image_address\"," +
                                "\"explanation\":\"이미지 설명\"" +
                                "}"))
                .andExpect(status().isNoContent())
                .andExpect(content().string(
                        containsString("\"image\"")
                ));
    }

    @Test
    void friendsPhotos() throws Exception {
        Username username = new Username("test");

        given(getFriendsPhotosService.list(username))
                .willReturn(new FriendsPhotosDto(List.of(FriendsPhotoDto.fake())));

        String token = jwtUtil.encode(username.getValue());

        mockMvc.perform(MockMvcRequestBuilders.get("/photos/friends")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"friendsPhotos\"")
                ));
    }
}
