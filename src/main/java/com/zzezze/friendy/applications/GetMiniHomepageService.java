package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.MiniHomepageDto;
import com.zzezze.friendy.exceptions.MiniHomepageNotFound;
import com.zzezze.friendy.exceptions.UserNotFound;
import com.zzezze.friendy.models.MiniHomepage;
import com.zzezze.friendy.models.Nickname;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.repositories.MiniHomepageRepository;
import com.zzezze.friendy.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GetMiniHomepageService {
    private final MiniHomepageRepository miniHomepageRepository;
    private final UserRepository userRepository;

    public GetMiniHomepageService(MiniHomepageRepository miniHomepageRepository, UserRepository userRepository) {
        this.miniHomepageRepository = miniHomepageRepository;
        this.userRepository = userRepository;
    }

    public MiniHomepageDto miniHomepage(Nickname nickname) {
        User user = userRepository.findByNickname(nickname)
                .orElseThrow(UserNotFound::new);

        MiniHomepage miniHomepage = miniHomepageRepository.findByUsername(user.getUsername())
                .orElseThrow(MiniHomepageNotFound::new);

        return miniHomepage.toDto();
    }
}
