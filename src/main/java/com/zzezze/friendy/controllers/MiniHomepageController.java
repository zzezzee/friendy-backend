package com.zzezze.friendy.controllers;

import com.zzezze.friendy.applications.GetMiniHomepageService;
import com.zzezze.friendy.dtos.MiniHomepageDto;
import com.zzezze.friendy.models.Nickname;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/miniHomepages")
public class MiniHomepageController {
    private final GetMiniHomepageService getMiniHomepageService;

    public MiniHomepageController(GetMiniHomepageService getMiniHomepageService) {
        this.getMiniHomepageService = getMiniHomepageService;
    }

    @GetMapping
    public MiniHomepageDto miniHomepage(
            @RequestParam Nickname nickname
    ) {
        MiniHomepageDto miniHomepageDto = getMiniHomepageService.miniHomepage(nickname);

        return miniHomepageDto;
    }
}
