package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.GuestBookDto;
import com.zzezze.friendy.dtos.GuestBooksDto;
import com.zzezze.friendy.exceptions.UserNotFound;
import com.zzezze.friendy.models.GuestBook;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.repositories.GuestBookRepository;
import com.zzezze.friendy.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class GetGuestBooksService {
    private final GuestBookRepository guestBookRepository;
    private final UserRepository userRepository;

    public GetGuestBooksService(GuestBookRepository guestBookRepository, UserRepository userRepository) {
        this.guestBookRepository = guestBookRepository;
        this.userRepository = userRepository;
    }

    public GuestBooksDto list(Nickname nickname) {
        User user = userRepository.findByNickname(nickname)
                .orElseThrow(UserNotFound::new);

        List<GuestBook> guestBooks = guestBookRepository.findAllByUsername(user.getUsername());

        List<GuestBookDto> guestBookDtos = guestBooks.stream()
                .map(GuestBook::toDto)
                .toList();

        return new GuestBooksDto(guestBookDtos);
    }
}
