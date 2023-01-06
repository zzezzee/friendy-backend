package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.GuestBookDto;
import com.zzezze.friendy.exceptions.GuestBookNouFound;
import com.zzezze.friendy.exceptions.UserNotFound;
import com.zzezze.friendy.models.GuestBook;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.value_objects.ProfileImage;
import com.zzezze.friendy.repositories.GuestBookRepository;
import com.zzezze.friendy.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GetGuestBookService {
    private final GuestBookRepository guestBookRepository;
    private final UserRepository userRepository;

    public GetGuestBookService(GuestBookRepository guestBookRepository, UserRepository userRepository) {
        this.guestBookRepository = guestBookRepository;
        this.userRepository = userRepository;
    }

    public GuestBookDto guestBook(Long id) {
        GuestBook guestBook = guestBookRepository.findById(id)
                .orElseThrow(GuestBookNouFound::new);

        User writer = userRepository.findByUsername(guestBook.getWriter())
                .orElseThrow(UserNotFound::new);

        ProfileImage profileImage = writer.getProfileImage();
        Nickname nickname = writer.getNickname();

        return guestBook.toDto(nickname, profileImage);
    }
}
