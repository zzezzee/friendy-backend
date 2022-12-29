package com.zzezze.friendy.controllers;

import com.zzezze.friendy.applications.GetPhotosService;
import com.zzezze.friendy.dtos.PhotosDto;
import com.zzezze.friendy.models.Nickname;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/photo-books")
public class PhotoController {
    private final GetPhotosService getPhotosService;

    public PhotoController(GetPhotosService getPhotosService) {
        this.getPhotosService = getPhotosService;
    }

    @GetMapping
    public PhotosDto photoBook(
            @RequestParam Nickname nickname
    ) {
        PhotosDto photosDto = getPhotosService.list(nickname);

        return photosDto;
    }
}
