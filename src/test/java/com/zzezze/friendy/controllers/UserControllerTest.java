package com.zzezze.friendy.controllers;

import com.zzezze.friendy.applications.GetUserService;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.Username;
import com.zzezze.friendy.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetUserService getUserService;

    @SpyBean
    private JwtUtil jwtUtil;

    @Test
    void user() throws Exception {
        Username username = new Username("zzezze");

        given(getUserService.detail(username))
                .willReturn(User.fake().toDto());

        String token = jwtUtil.encode(username.getValue());

        mockMvc.perform(MockMvcRequestBuilders.get("/users/me")
                        .header("Authorization", "Bearer " + token)
                )
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"nickname\":\"zzezze\"")
                ));
    }
}
