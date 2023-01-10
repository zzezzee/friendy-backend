package com.zzezze.friendy.controllers;

import com.zzezze.friendy.applications.GetRelationshipService;
import com.zzezze.friendy.dtos.UsersDto;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Nickname;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RelationshipController.class)
class RelationshipControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetRelationshipService getRelationshipService;

    @Test
    void list() throws Exception {
        given(getRelationshipService.list(any()))
                .willReturn(new UsersDto(List.of(User.fake().toDto())));

        mockMvc.perform(MockMvcRequestBuilders.get("/relationship?nickname=zzezze"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"users\"")
                ));
    }
}
