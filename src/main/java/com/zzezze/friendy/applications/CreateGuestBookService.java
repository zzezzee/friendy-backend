package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.GuestBookDto;
import com.zzezze.friendy.exceptions.MiniHomepageNotFound;
import com.zzezze.friendy.exceptions.UserNotFound;
import com.zzezze.friendy.models.GuestBook;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Content;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.GuestBookRepository;
import com.zzezze.friendy.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CreateGuestBookService {
    private final GuestBookRepository guestBookRepository;
    private final UserRepository userRepository;

    public CreateGuestBookService(GuestBookRepository guestBookRepository, UserRepository userRepository) {
        this.guestBookRepository = guestBookRepository;
        this.userRepository = userRepository;
    }

    public GuestBookDto create(Username writerUsername, Content content, Nickname receiverNickname) {
        User writer = userRepository.findByUsername(writerUsername)
                .orElseThrow(UserNotFound::new);

        User receiver = userRepository.findByNickname(receiverNickname)
                .orElseThrow(UserNotFound::new);

        GuestBook guestBook = GuestBook.of(writerUsername, receiver.getUsername(), content);

        guestBookRepository.save(guestBook);

        return guestBook.toDto(writer.getNickname(), writer.getProfileImage());
    }
}
