package com.zzezze.friendy.repositories;

import com.zzezze.friendy.models.Invitation;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.Username;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InvitationRepository extends JpaRepository<Invitation, Long> {
    List<Invitation> findAllBySender(Username username);

    List<Invitation> findAllByReceiver(Username username);


    Optional<Invitation> findBySenderAndReceiver(Username username, Username username1);

    void deleteByReceiver(Username username);
}
