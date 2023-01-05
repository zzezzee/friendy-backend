package com.zzezze.friendy.repositories;

import com.zzezze.friendy.models.MiniHomepage;
import com.zzezze.friendy.models.value_objects.Username;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MiniHomepageRepository extends JpaRepository<MiniHomepage, Long> {
    Optional<MiniHomepage> findByUsername(Username username);
}
