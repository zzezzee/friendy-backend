package com.zzezze.friendy.controllers;

import com.zzezze.friendy.applications.CreateCommentService;
import com.zzezze.friendy.applications.CreateReCommentService;
import com.zzezze.friendy.applications.DeleteCommentService;
import com.zzezze.friendy.applications.GetCommentsService;
import com.zzezze.friendy.applications.PatchCommentService;
import com.zzezze.friendy.dtos.CommentDto;
import com.zzezze.friendy.dtos.CommentsDto;
import com.zzezze.friendy.models.value_objects.Content;
import com.zzezze.friendy.models.value_objects.ParentId;
import com.zzezze.friendy.models.value_objects.PostId;
import com.zzezze.friendy.models.value_objects.PostType;
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
    private GetCommentsService getCommentsService;

    @MockBean
    private CreateCommentService createCommentService;

    @MockBean
    private PatchCommentService patchCommentService;

    @MockBean
    private DeleteCommentService deleteCommentService;

    @MockBean
    private CreateReCommentService createReCommentService;

    @SpyBean
    private JwtUtil jwtUtil;

    @Test
    void list() throws Exception {
        given(getCommentsService.list(any()))
                .willReturn(new CommentsDto(List.of(CommentDto.fake())));

        mockMvc.perform(MockMvcRequestBuilders.get("/comments?id=1"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("comments")
                ));
    }

    @Test
    void create() throws Exception {
        Username username = new Username("test");
        PostId postId = new PostId(1L);
        PostType postType = new PostType("photo");
        Content content = new Content("댓글 내용");

        given(createCommentService.create(username, postId, postType, content, nickname))
                .willReturn(1L);

        String token = jwtUtil.encode(username.getValue());

        mockMvc.perform(MockMvcRequestBuilders.post("/comments")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"content\":\"댓글 내용\"," +
                                "\"postId\":\"1\"," +
                                "\"postType\":\"photo\"" +
                                "}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(
                        containsString("1")
                ));
    }

    @Test
    void createReComment() throws Exception {
        Username username = new Username("test");
        PostId postId = new PostId(1L);
        PostType postType = new PostType("photo");
        Content content = new Content("댓글 내용");
        ParentId parentId = new ParentId(1L);

        given(createReCommentService.create(username, postId, postType, content, parentId))
                .willReturn(1L);

        String token = jwtUtil.encode(username.getValue());

        mockMvc.perform(MockMvcRequestBuilders.post("/comments/reply")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"content\":\"댓글 내용\"," +
                                "\"postId\":\"1\"," +
                                "\"postType\":\"photo\"," +
                                "\"parentId\":\"1\"" +
                                "}"))
                .andExpect(status().isCreated())
                .andExpect(content().string(
                        containsString("1")
                ));
    }


    @Test
    void patch() throws Exception {
        Username username = new Username("test");

        given(patchCommentService.patch(any(), any(), any()))
                .willReturn(1L);

        String token = jwtUtil.encode(username.getValue());

        mockMvc.perform(MockMvcRequestBuilders.patch("/comments/1")
                        .header("Authorization", "Bearer " + token)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{" +
                                "\"content\":\"수정된 내용\"," +
                                "\"id\":\"1\"" +
                                "}"))
                .andExpect(status().isNoContent())
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
