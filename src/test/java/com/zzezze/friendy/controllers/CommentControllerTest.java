package com.zzezze.friendy.controllers;

import com.zzezze.friendy.applications.CreateCommentService;
import com.zzezze.friendy.applications.DeleteCommentService;
import com.zzezze.friendy.applications.GetPhotoCommentsService;
import com.zzezze.friendy.dtos.CommentDto;
import com.zzezze.friendy.dtos.CommentsDto;
import com.zzezze.friendy.models.value_objects.Content;
import com.zzezze.friendy.models.value_objects.PhotoId;
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

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
class CommentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetPhotoCommentsService getCommentsService;

    @MockBean
    private CreateCommentService createCommentService;

    @MockBean
    private DeleteCommentService deleteCommentService;

    @SpyBean
    private JwtUtil jwtUtil;

    @Test
    void list() throws Exception {
        given(getCommentsService.list(any()))
                .willReturn(new CommentsDto(List.of(CommentDto.fake())));

        mockMvc.perform(MockMvcRequestBuilders.get("/comments?photoId=1"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("comments")
                ));
    }

    @Test
    void create() throws Exception {
        Username username = new Username("test");
        PhotoId photoId = new PhotoId(1L);
        Content content = new Content("댓글 내용");

        given(createCommentService.create(username, photoId, content))
                .willReturn(1L);

        String token = jwtUtil.encode(username.getValue());

        mockMvc.perform(MockMvcRequestBuilders.post("/comments")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"content\":\"댓글 내용\"," +
                                "\"photoId\":\"1\"" +
                                "}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(
                        containsString("1")
                ));
    }

    @Test
    void delete() throws Exception {
        Username username = new Username("test");

        given(deleteCommentService.delete(username, 1L))
                .willReturn(1L);

        String token = jwtUtil.encode(username.getValue());

        mockMvc.perform(MockMvcRequestBuilders.delete("/comments/1")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andExpect(content().string(
                        containsString("1")
                ));
    }
}
