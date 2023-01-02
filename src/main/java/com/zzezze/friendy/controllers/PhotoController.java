package com.zzezze.friendy.controllers;

import com.zzezze.friendy.applications.CreatePhotoService;
import com.zzezze.friendy.applications.DeletePhotoService;
import com.zzezze.friendy.applications.GetPhotosService;
import com.zzezze.friendy.dtos.PhotoDeleteResponseDto;
import com.zzezze.friendy.dtos.PhotoDto;
import com.zzezze.friendy.dtos.PhotoRegistrationDto;
import com.zzezze.friendy.dtos.PhotosDto;
import com.zzezze.friendy.models.Explanation;
import com.zzezze.friendy.models.Image;
import com.zzezze.friendy.models.Nickname;
import com.zzezze.friendy.models.Username;
import com.zzezze.friendy.utils.S3Uploader;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class PhotoController {
    private final GetPhotosService getPhotosService;
    private final CreatePhotoService createPhotoService;
    private final DeletePhotoService deletePhotoService;

    private final S3Uploader s3Uploader;

    public PhotoController(GetPhotosService getPhotosService, S3Uploader s3Uploader, CreatePhotoService createPhotoService, DeletePhotoService deletePhotoService) {
        this.getPhotosService = getPhotosService;
        this.s3Uploader = s3Uploader;
        this.createPhotoService = createPhotoService;
        this.deletePhotoService = deletePhotoService;
    }

    @GetMapping("/photo-books")
    public PhotosDto photoBook(
            @RequestParam Nickname nickname
    ) {
        PhotosDto photosDto = getPhotosService.list(nickname);

        return photosDto;
    }

    @PostMapping("/photo-books")
    @ResponseStatus(HttpStatus.CREATED)
    public PhotoDto photo(
            @RequestAttribute("username") Username username,
            @RequestBody PhotoRegistrationDto photoRegistrationDto
    ) {
        Image image = new Image(photoRegistrationDto.getImage());
        Explanation explanation = new Explanation(photoRegistrationDto.getExplanation());

        PhotoDto photoDto = createPhotoService.create(username, image, explanation);

        return photoDto;
    }

    @DeleteMapping("/photo-books/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public PhotoDeleteResponseDto delete(
            @RequestAttribute("username") Username username,
            @PathVariable Long id
    ) {
        PhotoDeleteResponseDto photoDeleteResponseDto= deletePhotoService.delete(username, id);

        return photoDeleteResponseDto;
    }

    @PostMapping("/upload-photo")
    public String upload(MultipartFile multipartFile) throws IOException {
        return s3Uploader.uploadFiles(multipartFile, "photoImage");
    }
}
