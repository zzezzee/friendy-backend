package com.zzezze.friendy.controllers;

import com.zzezze.friendy.applications.PatchLikeService;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.value_objects.PhotoId;
import com.zzezze.friendy.models.value_objects.Username;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("likes")
public class LikeController {
    private final PatchLikeService patchLikeService;

    public LikeController(PatchLikeService patchLikeService) {
        this.patchLikeService = patchLikeService;
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void patch(
            @RequestAttribute("username") Username username,
            @RequestParam Long photoId
    ) {
        patchLikeService.patch(username, new PhotoId(photoId));
    }
}
