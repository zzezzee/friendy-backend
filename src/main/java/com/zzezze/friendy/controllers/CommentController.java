package com.zzezze.friendy.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.zzezze.friendy.applications.CreateCommentService;
import com.zzezze.friendy.applications.CreateReCommentService;
import com.zzezze.friendy.applications.DeleteCommentService;
import com.zzezze.friendy.applications.GetCommentsService;
import com.zzezze.friendy.applications.PatchCommentService;
import com.zzezze.friendy.dtos.CommentCreateDto;
import com.zzezze.friendy.dtos.CommentEditDto;
import com.zzezze.friendy.dtos.CommentsDto;
import com.zzezze.friendy.dtos.ReCommentCreateDto;
import com.zzezze.friendy.models.value_objects.Content;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.value_objects.ParentId;
import com.zzezze.friendy.models.value_objects.PostId;
import com.zzezze.friendy.models.value_objects.PostType;
import com.zzezze.friendy.models.value_objects.Username;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("comments")
public class CommentController {
    private final GetCommentsService getCommentsService;
    private final CreateCommentService createCommentService;
    private final PatchCommentService patchCommentService;
    private final DeleteCommentService deleteCommentService;
    private final CreateReCommentService createReCommentService;

    public CommentController(GetCommentsService getCommentsService, CreateCommentService createCommentService, PatchCommentService patchCommentService, DeleteCommentService deleteCommentService, CreateReCommentService createReCommentService) {
        this.getCommentsService = getCommentsService;
        this.createCommentService = createCommentService;
        this.patchCommentService = patchCommentService;
        this.deleteCommentService = deleteCommentService;
        this.createReCommentService = createReCommentService;
    }

    @GetMapping
    public CommentsDto list(
            @RequestParam Long id
    ) {
        CommentsDto commentsDto = getCommentsService.list(new PostId(id));

        return commentsDto;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(
            @RequestAttribute("username") Username username,
            @RequestBody CommentCreateDto commentCreateDto
    ) throws JsonProcessingException {
        PostId postId = new PostId(commentCreateDto.getPostId());
        PostType postType = new PostType(commentCreateDto.getPostType());
        Content content = new Content(commentCreateDto.getContent());
        Nickname nickname = new Nickname(commentCreateDto.getMiniHomepageOwner());

        Long id = createCommentService.create(username, postId, postType, content, nickname);

        return id;
    }

    @PostMapping("reply")
    @ResponseStatus(HttpStatus.CREATED)
    public Long createReComment(
            @RequestAttribute("username") Username username,
            @RequestBody ReCommentCreateDto reCommentCreateDto
    ) {
        PostId postId = new PostId(reCommentCreateDto.getPostId());
        PostType postType = new PostType(reCommentCreateDto.getPostType());
        Content content = new Content(reCommentCreateDto.getContent());
        ParentId parentId = new ParentId(reCommentCreateDto.getParentId());

        Long id = createReCommentService.create(username, postId, postType, content, parentId);

        return id;
    }

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Long patch(
            @RequestAttribute("username") Username username,
            @RequestBody CommentEditDto commentEditDto
    ) {
        Long id = commentEditDto.getId();
        Content content = new Content(commentEditDto.getContent());

        Long patchId = patchCommentService.patch(username, id, content);

        return patchId;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Long delete(
            @RequestAttribute("username") Username username,
            @PathVariable Long id
    ) {
        Long deletedId = deleteCommentService.delete(username, id);

        return deletedId;
    }
}
