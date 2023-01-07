package com.zzezze.friendy.repositories;

import com.zzezze.friendy.models.Photo;
import com.zzezze.friendy.models.value_objects.Username;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findAllByUsername(Username username);

    Photo save(Photo photo);
}
