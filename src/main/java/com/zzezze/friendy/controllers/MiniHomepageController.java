package com.zzezze.friendy.controllers;

import com.zzezze.friendy.applications.GetMiniHomepageService;
import com.zzezze.friendy.applications.PatchMiniHomepageService;
import com.zzezze.friendy.dtos.MiniHomepageDto;
import com.zzezze.friendy.dtos.MiniHomepageEditDto;
import com.zzezze.friendy.models.Introduction;
import com.zzezze.friendy.models.Nickname;
import com.zzezze.friendy.models.ProfileImage;
import com.zzezze.friendy.models.Username;
import com.zzezze.friendy.utils.S3Uploader;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class MiniHomepageController {
    private final GetMiniHomepageService getMiniHomepageService;
    private final S3Uploader s3Uploader;
    private final PatchMiniHomepageService patchMiniHomepageService;

    public MiniHomepageController(GetMiniHomepageService getMiniHomepageService, S3Uploader s3Uploader, PatchMiniHomepageService patchMiniHomepageService) {
        this.getMiniHomepageService = getMiniHomepageService;
        this.s3Uploader = s3Uploader;
        this.patchMiniHomepageService = patchMiniHomepageService;
    }

    @GetMapping("/miniHomepages")
    public MiniHomepageDto miniHomepage(
            @RequestParam Nickname nickname
    ) {
        MiniHomepageDto miniHomepageDto = getMiniHomepageService.miniHomepage(nickname);

        return miniHomepageDto;
    }

    @PatchMapping("/miniHomepages")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public MiniHomepageDto edit(
            @RequestAttribute("username") Username username,
            @RequestBody MiniHomepageEditDto miniHomepageEditDto
    ) {
        ProfileImage profileImage = new ProfileImage(miniHomepageEditDto.getProfileImage());
        Introduction introduction = new Introduction(miniHomepageEditDto.getIntroduction());

        MiniHomepageDto miniHomepageDto = patchMiniHomepageService.patch(
                username,
                profileImage,
                introduction
        );

        return miniHomepageDto;
    }

    @PostMapping("/upload")
    public String upload(MultipartFile multipartFile) throws IOException {
        return s3Uploader.uploadFiles(multipartFile, "profileImage");
    }
}
