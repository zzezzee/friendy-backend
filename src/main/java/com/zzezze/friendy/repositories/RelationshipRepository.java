package com.zzezze.friendy.repositories;

import com.zzezze.friendy.models.Relationship;
import com.zzezze.friendy.models.value_objects.Username;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface RelationshipRepository extends JpaRepository<Relationship, Long> {
    List<Relationship> findAllBySender(Username username);

    List<Relationship> findAllByReceiver(Username username);

    List<Relationship> findAllBySenderOrReceiver(Username visitorUsername, Username visitorUsername1);
}
