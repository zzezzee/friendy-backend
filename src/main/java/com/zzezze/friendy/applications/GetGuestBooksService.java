package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.GuestBookDto;
import com.zzezze.friendy.dtos.GuestBooksDto;
import com.zzezze.friendy.exceptions.MiniHomepageNotFound;
import com.zzezze.friendy.exceptions.UserNotFound;
import com.zzezze.friendy.models.GuestBook;
import com.zzezze.friendy.models.MiniHomepage;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.repositories.GuestBookRepository;
import com.zzezze.friendy.repositories.MiniHomepageRepository;
import com.zzezze.friendy.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class GetGuestBooksService {
    private final GuestBookRepository guestBookRepository;
    private final UserRepository userRepository;
    private MiniHomepageRepository miniHomepageRepository;

    public GetGuestBooksService(GuestBookRepository guestBookRepository, UserRepository userRepository, MiniHomepageRepository miniHomepageRepository) {
        this.guestBookRepository = guestBookRepository;
        this.userRepository = userRepository;
        this.miniHomepageRepository = miniHomepageRepository;
    }

    public GuestBooksDto list(Nickname nickname) {
        User user = userRepository.findByNickname(nickname)
                .orElseThrow(UserNotFound::new);

        List<GuestBook> guestBooks = guestBookRepository.findAllByUsername(user.getUsername());

        List<GuestBookDto> guestBookDtos = guestBooks.stream()
                .map(guestBook -> {
                    MiniHomepage miniHomepage = miniHomepageRepository.findByUsername(guestBook.getWriter())
                            .orElseThrow(MiniHomepageNotFound::new);

                    return guestBook.toDto(miniHomepage.getNickname(), miniHomepage.getProfileImage());
                })
                .toList();

        return new GuestBooksDto(guestBookDtos);
    }
}
