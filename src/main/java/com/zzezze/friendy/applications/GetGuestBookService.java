package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.GuestBookDto;
import com.zzezze.friendy.exceptions.GuestBookNouFound;
import com.zzezze.friendy.models.GuestBook;
import com.zzezze.friendy.repositories.GuestBookRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class GetGuestBookService {
    private final GuestBookRepository guestBookRepository;

    public GetGuestBookService(GuestBookRepository guestBookRepository) {
        this.guestBookRepository = guestBookRepository;
    }

    public GuestBookDto guestBook(Long id) {
        GuestBook guestBook = guestBookRepository.findById(id)
                .orElseThrow(GuestBookNouFound::new);

        return guestBook.toDto();
    }
}
