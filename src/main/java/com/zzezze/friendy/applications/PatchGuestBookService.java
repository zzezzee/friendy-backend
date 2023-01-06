package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.GuestBookDto;
import com.zzezze.friendy.dtos.GuestBookPatchResponseDto;
import com.zzezze.friendy.exceptions.GuestBookNouFound;
import com.zzezze.friendy.exceptions.PatchGuestBookFailed;
import com.zzezze.friendy.models.GuestBook;
import com.zzezze.friendy.models.value_objects.Content;
import com.zzezze.friendy.models.value_objects.Username;
import com.zzezze.friendy.repositories.GuestBookRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class PatchGuestBookService {
    private final GuestBookRepository guestBookRepository;

    public PatchGuestBookService(GuestBookRepository guestBookRepository) {
        this.guestBookRepository = guestBookRepository;
    }

    public GuestBookPatchResponseDto patch(Username username, Long id, Content content) {
        GuestBook guestBook = guestBookRepository.findById(id)
                .orElseThrow(GuestBookNouFound::new);

        Username writer = guestBook.getWriter();

        if(!writer.equals(username)){
            throw new PatchGuestBookFailed();
        }

        guestBook.change(content);

        return new GuestBookPatchResponseDto(id);
    }
}
