package com.zzezze.friendy.repositories;

import com.zzezze.friendy.models.Comment;
import com.zzezze.friendy.models.value_objects.ParentId;
import com.zzezze.friendy.models.value_objects.PhotoId;
import com.zzezze.friendy.models.value_objects.PostId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPostId(PostId id);

    List<Comment> findAllByPostIdAndParentId(PostId id, Object o);

    List<Comment> findAllByParentId(ParentId parentId);
}
