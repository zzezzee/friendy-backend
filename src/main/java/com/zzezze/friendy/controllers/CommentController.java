package com.zzezze.friendy.controllers;

import com.zzezze.friendy.applications.CreateCommentService;
import com.zzezze.friendy.applications.DeleteCommentService;
import com.zzezze.friendy.dtos.CommentCreateDto;
import com.zzezze.friendy.models.value_objects.Content;
import com.zzezze.friendy.models.value_objects.PhotoId;
import com.zzezze.friendy.models.value_objects.Username;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("comments")
public class CommentController {
    private final CreateCommentService createCommentService;
    private final DeleteCommentService deleteCommentService;

    public CommentController(CreateCommentService createCommentService, DeleteCommentService deleteCommentService) {
        this.createCommentService = createCommentService;
        this.deleteCommentService = deleteCommentService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(
            @RequestAttribute("username") Username username,
            @RequestBody CommentCreateDto commentCreateDto
    ) {
        PhotoId photoId = new PhotoId(commentCreateDto.getPhotoId());
        Content content = new Content(commentCreateDto.getContent());

        Long id = createCommentService.create(username, photoId, content);

        return id;
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
