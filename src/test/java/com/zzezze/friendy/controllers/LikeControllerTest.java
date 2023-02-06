package com.zzezze.friendy.controllers;

import com.zzezze.friendy.applications.PatchLikeService;
import com.zzezze.friendy.models.Photo;
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

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LikeController.class)
class LikeControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatchLikeService patchLikeService;

    @SpyBean
    private JwtUtil jwtUtil;

    @Test
    void patch() throws Exception {
        Username username = new Username("test");

        String token = jwtUtil.encode(username.getValue());

        mockMvc.perform(MockMvcRequestBuilders.patch("/likes?photoId=1")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isNoContent());
    }

}
