package com.zzezze.friendy.applications;

import com.zzezze.friendy.dtos.CommentDto;
import com.zzezze.friendy.dtos.CommentsDto;
import com.zzezze.friendy.exceptions.UserNotFound;
import com.zzezze.friendy.models.Comment;
import com.zzezze.friendy.models.User;
import com.zzezze.friendy.models.value_objects.PhotoId;
import com.zzezze.friendy.repositories.CommentRepository;
import com.zzezze.friendy.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class GetPhotoCommentsService {
    private final CommentRepository commentRepository;
    private final UserRepository userRepository;

    public GetPhotoCommentsService(CommentRepository commentRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.userRepository = userRepository;
    }

    public CommentsDto list(PhotoId id) {
        List<Comment> comments = commentRepository.findAllByPhotoId(id);

        List<CommentDto> commentDtos = comments.stream()
                .map(comment -> {
                    User writer = userRepository.findByUsername(comment.getUsername())
                            .orElseThrow(UserNotFound::new);

                    return comment.toDto(writer.getProfileImage(), writer.getNickname());
                })
                .toList();

        return new CommentsDto(commentDtos);
    }
}
