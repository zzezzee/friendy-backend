package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.GuestBookDeleteResponseDto;
import com.zzezze.friendy.exceptions.GuestBookDeleteFailed;
import com.zzezze.friendy.exceptions.GuestBookNouFound;
import com.zzezze.friendy.exceptions.UserNotFound;
import com.zzezze.friendy.models.GuestBook;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.GuestBookRepository;
import com.zzezze.friendy.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DeleteGuestBookService {
    private GuestBookRepository guestBookRepository;
    private UserRepository userRepository;

    public DeleteGuestBookService(GuestBookRepository guestBookRepository, UserRepository userRepository) {
        this.guestBookRepository = guestBookRepository;
        this.userRepository = userRepository;
    }

    public GuestBookDeleteResponseDto delete(Username username, Long id) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(UserNotFound::new);

        GuestBook guestBook = guestBookRepository.findById(id)
                .orElseThrow(GuestBookNouFound::new);

        if (!guestBook.getUsername().equals(user.getUsername())
                && !guestBook.getWriter().getValue().equals(user.getUsername().getValue())) {
            throw new GuestBookDeleteFailed();
        }

        guestBookRepository.deleteById(id);

        return new GuestBookDeleteResponseDto(id);
    }
}
