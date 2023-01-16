package com.zzezze.friendy.controllers;

import com.zzezze.friendy.applications.CreatePhotoService;
import com.zzezze.friendy.applications.DeletePhotoService;
import com.zzezze.friendy.applications.GetPhotoDetailService;
import com.zzezze.friendy.applications.GetPhotosService;
import com.zzezze.friendy.applications.PatchPhotoService;
import com.zzezze.friendy.dtos.PhotoDeleteResponseDto;
import com.zzezze.friendy.dtos.PhotoDetailDto;
import com.zzezze.friendy.dtos.PhotoDto;
import com.zzezze.friendy.dtos.PhotoPatchRequestDto;
import com.zzezze.friendy.dtos.PhotoRegistrationDto;
import com.zzezze.friendy.dtos.PhotosDto;
import com.zzezze.friendy.models.value_objects.Explanation;
import com.zzezze.friendy.models.value_objects.Image;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.utils.S3Uploader;
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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("photos")
public class PhotoController {
    private final GetPhotosService getPhotosService;
    private final GetPhotoDetailService getPhotoDetailService;
    private final CreatePhotoService createPhotoService;
    private final DeletePhotoService deletePhotoService;
    private final PatchPhotoService patchPhotoService;

    private final S3Uploader s3Uploader;

    public PhotoController(GetPhotosService getPhotosService, GetPhotoDetailService getPhotoDetailService, CreatePhotoService createPhotoService, DeletePhotoService deletePhotoService, PatchPhotoService patchPhotoService, S3Uploader s3Uploader) {
        this.getPhotosService = getPhotosService;
        this.getPhotoDetailService = getPhotoDetailService;
        this.createPhotoService = createPhotoService;
        this.deletePhotoService = deletePhotoService;
        this.patchPhotoService = patchPhotoService;
        this.s3Uploader = s3Uploader;
    }

    @GetMapping
    public PhotosDto photoBook(
            @RequestParam Nickname nickname
    ) {
        PhotosDto photosDto = getPhotosService.list(nickname);

        return photosDto;
    }

    @GetMapping("{id}")
    public PhotoDetailDto photo(
            @PathVariable Long id
    ) {
        PhotoDetailDto photoDetailDto = getPhotoDetailService.detail(id);

        return photoDetailDto;
    }

    @PostMapping
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

    @PatchMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public PhotoDto patch(
            @RequestAttribute("username") Username username,
            @RequestBody PhotoPatchRequestDto photoEditRequestDto
    ) {
        Long id = photoEditRequestDto.getId();
        Image image = new Image(photoEditRequestDto.getImage());
        Explanation explanation = new Explanation(photoEditRequestDto.getExplanation());

        PhotoDto photoDto= patchPhotoService.patch(username, id, image, explanation);

        return photoDto;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public PhotoDeleteResponseDto delete(
            @RequestAttribute("username") Username username,
            @PathVariable Long id
    ) {
        PhotoDeleteResponseDto photoDeleteResponseDto= deletePhotoService.delete(username, id);

        return photoDeleteResponseDto;
    }

    @PostMapping("upload")
    public String upload(MultipartFile multipartFile) throws IOException {
        return s3Uploader.uploadFiles(multipartFile, "photoImage");
    }
}
