package com.zzezze.friendy.controllers;

import com.zzezze.friendy.applications.GetMiniHomepageService;
import com.zzezze.friendy.models.MiniHomepage;
import com.zzezze.friendy.models.Nickname;
import com.zzezze.friendy.models.Username;
import com.zzezze.friendy.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MiniHomepageController.class)
class MiniHomepageControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetMiniHomepageService getMiniHomepageService;

    @SpyBean
    private JwtUtil jwtUtil;

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
}
