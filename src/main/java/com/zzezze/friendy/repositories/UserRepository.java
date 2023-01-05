package com.zzezze.friendy.repositories;

import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Username;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(Username username);

    Optional<User> findByNickname(Nickname nickname);
}
