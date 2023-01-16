package com.zzezze.friendy.repositories;

import com.zzezze.friendy.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByParent(Long id);
}
