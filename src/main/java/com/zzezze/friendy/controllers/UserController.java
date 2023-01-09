package com.zzezze.friendy.controllers;

import com.zzezze.friendy.applications.GetUserProfileService;
import com.zzezze.friendy.applications.GetUserService;
import com.zzezze.friendy.applications.GetUsersService;
import com.zzezze.friendy.applications.PatchUserProfileService;
import com.zzezze.friendy.dtos.UserProfilePatchRequestDto;
import com.zzezze.friendy.dtos.UserDto;
import com.zzezze.friendy.dtos.UserRelationShipDto;
import com.zzezze.friendy.dtos.UsersDto;
import com.zzezze.friendy.models.value_objects.Introduction;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.value_objects.ProfileImage;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.utils.S3Uploader;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
@RequestMapping("/users")
public class UserController {
    private final GetUserService getUserService;
    private final GetUserProfileService getUserProfileService;
    private final PatchUserProfileService patchUserProfileService;
    private final GetUsersService getUsersService;
    private final S3Uploader s3Uploader;

    public UserController(GetUserService getUserService, GetUserProfileService getUserProfileService, PatchUserProfileService patchUserProfileService, GetUsersService getUsersService, S3Uploader s3Uploader) {
        this.getUserService = getUserService;
        this.getUserProfileService = getUserProfileService;
        this.patchUserProfileService = patchUserProfileService;
        this.getUsersService = getUsersService;
        this.s3Uploader = s3Uploader;
    }

    @GetMapping("/me")
    public UserRelationShipDto user(
            @RequestAttribute("username") Username username,
            @RequestParam Nickname currentNickname
    ) {
        UserRelationShipDto userRelationShipDto = getUserService.detail(username, currentNickname);

        return userRelationShipDto;
    }

    @GetMapping("profile")
    public UserDto profile(
            @RequestParam Nickname nickname
    ) {
        UserDto UserDto = getUserProfileService.profile(nickname);

        return UserDto;
    }

    @GetMapping
    public UsersDto list() {
        UsersDto usersDto = getUsersService.list();

        return usersDto;
    }

    @PatchMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public UserDto edit(
            @RequestAttribute("username") Username username,
            @RequestBody UserProfilePatchRequestDto userProfilePatchRequestDto
    ) {
        ProfileImage profileImage = new ProfileImage(userProfilePatchRequestDto.getProfileImage());
        Introduction introduction = new Introduction(userProfilePatchRequestDto.getIntroduction());

        UserDto UserDto = patchUserProfileService.patch(
                username,
                profileImage,
                introduction
        );

        return UserDto;
    }

    @PostMapping("upload-image")
    public String upload(MultipartFile multipartFile) throws IOException {
        return s3Uploader.uploadFiles(multipartFile, "profileImage");
    }
}
