package com.zzezze.friendy.controllers;

import com.zzezze.friendy.applications.GetInvitationsService;
import com.zzezze.friendy.dtos.InvitationsDto;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.utils.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(InvitationController.class)
class InvitationControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetInvitationsService getInvitesService;

    @SpyBean
    private JwtUtil jwtUtil;


    @Test
    void list() throws Exception {
        Username username = new Username("test");

        given(getInvitesService.list(username))
                .willReturn(new InvitationsDto(
                        List.of(User.fake().toDto()),
                        List.of(User.fake().toDto())
                ));

        String token = jwtUtil.encode(username.getValue());

        mockMvc.perform(MockMvcRequestBuilders.get("/invitations")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("invitationsReceived")
                ));
    }
}
