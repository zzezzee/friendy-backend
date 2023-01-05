package com.zzezze.friendy.controllers;

import com.zzezze.friendy.applications.GetMiniHomepageService;
import com.zzezze.friendy.applications.PatchMiniHomepageService;
import com.zzezze.friendy.models.value_objects.Introduction;
import com.zzezze.friendy.models.MiniHomepage;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.value_objects.ProfileImage;
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

@WebMvcTest(MiniHomepageController.class)
class MiniHomepageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetMiniHomepageService getMiniHomepageService;

    @MockBean
    private PatchMiniHomepageService patchMiniHomepageService;

    @SpyBean
    private JwtUtil jwtUtil;

    @MockBean
    private S3Uploader s3Uploader;

    @Test
    void miniHomepage() throws Exception {
        Nickname nickname = new Nickname("zzezze");

        given(getMiniHomepageService.miniHomepage(nickname))
                .willReturn(MiniHomepage.fake().toDto());

        mockMvc.perform(MockMvcRequestBuilders.get("/miniHomepages?nickname=zzezze"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"profileImage\"")
                ));
    }

    @Test
    void edit() throws Exception {
        Username username = new Username("test");
        ProfileImage profileImage = new ProfileImage("image_address");
        Introduction introduction = new Introduction("introduction");

        given(patchMiniHomepageService.patch(username, profileImage, introduction))
                .willReturn(MiniHomepage.fake().toDto());

        String token = jwtUtil.encode(username.getValue());

        mockMvc.perform(MockMvcRequestBuilders.patch("/miniHomepages")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"profileImage\":\"image_address\"," +
                                "\"introduction\":\"introduction\"" +
                                "}"))
                .andExpect(status().isNoContent())
                .andExpect(content().string(
                        containsString("\"introduction\"")
                ));
    }
}
