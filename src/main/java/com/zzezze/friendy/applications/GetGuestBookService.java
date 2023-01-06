package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.GuestBookDto;
import com.zzezze.friendy.exceptions.GuestBookNouFound;
import com.zzezze.friendy.exceptions.MiniHomepageNotFound;
import com.zzezze.friendy.models.GuestBook;
import com.zzezze.friendy.models.MiniHomepage;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.value_objects.ProfileImage;
import com.zzezze.friendy.repositories.GuestBookRepository;
import com.zzezze.friendy.repositories.MiniHomepageRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GetGuestBookService {
    private final GuestBookRepository guestBookRepository;
    private final MiniHomepageRepository miniHomepageRepository;

    public GetGuestBookService(GuestBookRepository guestBookRepository, MiniHomepageRepository miniHomepageRepository) {
        this.guestBookRepository = guestBookRepository;
        this.miniHomepageRepository = miniHomepageRepository;
    }

    public GuestBookDto guestBook(Long id) {
        GuestBook guestBook = guestBookRepository.findById(id)
                .orElseThrow(GuestBookNouFound::new);

        MiniHomepage miniHomepage = miniHomepageRepository.findByUsername(guestBook.getWriter())
                .orElseThrow(MiniHomepageNotFound::new);

        ProfileImage profileImage = miniHomepage.getProfileImage();
        Nickname nickname = miniHomepage.getNickname();

        return guestBook.toDto(nickname, profileImage);
    }
}
