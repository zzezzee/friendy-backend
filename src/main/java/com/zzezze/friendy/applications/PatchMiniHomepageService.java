package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.MiniHomepageDto;
import com.zzezze.friendy.exceptions.MiniHomepageNotFound;
import com.zzezze.friendy.models.value_objects.Introduction;
import com.zzezze.friendy.models.MiniHomepage;
import com.zzezze.friendy.models.value_objects.ProfileImage;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.MiniHomepageRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PatchMiniHomepageService {
    private final MiniHomepageRepository miniHomepageRepository;

    public PatchMiniHomepageService(MiniHomepageRepository miniHomepageRepository) {
        this.miniHomepageRepository = miniHomepageRepository;
    }

    public MiniHomepageDto patch(Username username, ProfileImage profileImage, Introduction introduction) {
        MiniHomepage miniHomepage = miniHomepageRepository.findByUsername(username)
                .orElseThrow(MiniHomepageNotFound::new);

        miniHomepage.change(profileImage, introduction);

        return miniHomepage.toDto();
    }
}
