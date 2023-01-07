package com.zzezze.friendy.repositories;

import com.zzezze.friendy.models.GuestBook;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.value_objects.Username;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GuestBookRepository extends JpaRepository<GuestBook, Long> {
    List<GuestBook> findAllByUsername(Username username);
}
