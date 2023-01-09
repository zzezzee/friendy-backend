package com.zzezze.friendy.controllers;

import com.zzezze.friendy.applications.GetUserProfileService;
import com.zzezze.friendy.applications.GetUserService;
import com.zzezze.friendy.applications.PatchUserProfileService;
import com.zzezze.friendy.dtos.UserRelationShipDto;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Nickname;
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

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetUserService getUserService;

    @MockBean
    private GetUserProfileService getUserProfileService;

    @MockBean
    private PatchUserProfileService patchUserProfileService;

    @MockBean
    private S3Uploader s3Uploader;

    @SpyBean
    private JwtUtil jwtUtil;

    @Test
    void user() throws Exception {
        Username username = new Username("test");
        Nickname nickname = new Nickname("zzezze");

        given(getUserService.detail(username, nickname))
                .willReturn(UserRelationShipDto.fake());

        String token = jwtUtil.encode(username.getValue());

        mockMvc.perform(MockMvcRequestBuilders.get("/users/me")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"currentNickname\":\"zzezze\"" +
                                "}"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"nickname\":\"zzezze\"")
                ));
    }

    @Test
    void profile() throws Exception {
        Nickname nickname = new Nickname("zzezze");

        given(getUserProfileService.profile(nickname))
                .willReturn(User.fake().toDto());

        mockMvc.perform(MockMvcRequestBuilders.get("/users/profile?nickname=zzezze"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"nickname\":\"zzezze\"")
                ));
    }

    @Test
    void patch() throws Exception {
        Username username = new Username("test");

        given(patchUserProfileService.patch(any(), any(), any()))
                .willReturn(User.fake().toDto());

        String token = jwtUtil.encode(username.getValue());

        mockMvc.perform(MockMvcRequestBuilders.patch("/users")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"profileImage\":\"image_address\"," +
                                "\"introduction\":\"프로필 설명\"" +
                                "}"))
                .andExpect(status().isNoContent())
                .andExpect(content().string(
                        containsString("\"profileImage\"")
                ));
    }
}
