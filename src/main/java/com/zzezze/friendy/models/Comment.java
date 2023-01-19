package com.zzezze.friendy.models;

import com.zzezze.friendy.dtos.CommentDto;
import com.zzezze.friendy.dtos.ReCommentDto;
import com.zzezze.friendy.models.value_objects.Content;
import com.zzezze.friendy.models.value_objects.Nickname;
import com.zzezze.friendy.models.value_objects.ParentId;
import com.zzezze.friendy.models.value_objects.PostId;
import com.zzezze.friendy.models.value_objects.PostType;
import com.zzezze.friendy.models.value_objects.ProfileImage;
import com.zzezze.friendy.models.value_objects.Username;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    @Embedded
    private ParentId parentId;

    @Embedded
    private PostId postId;

    @Embedded
    private PostType postType;

    @Embedded
    private Username username;

    @Embedded
    private Content content;

    @CreationTimestamp
    private LocalDateTime createdAt;

    public Comment() {
    }

    public Comment(Long id, ParentId parentId, PostId postId, PostType postType, Username username, Content content, LocalDateTime createdAt) {
        this.id = id;
        this.parentId = parentId;
        this.postId = postId;
        this.postType = postType;
        this.username = username;
        this.content = content;
        this.createdAt = createdAt;
    }

    public Comment(ParentId parentId, PostId postId, PostType postType, Username username, Content content) {
        this.parentId = parentId;
        this.postId = postId;
        this.postType = postType;
        this.username = username;
        this.content = content;
    }

    public Comment(PostId postId, PostType postType, Username username, Content content) {
        this.postId = postId;
        this.postType = postType;
        this.username = username;
        this.content = content;
    }

    public static Comment of(PostId postId, PostType postType, Username username, Content content) {
        return new Comment(postId, postType, username, content);
    }

    public Long getId() {
        return id;
    }

    public Username getUsername() {
        return username;
    }

    public Content getContent() {
        return content;
    }

    public void changeContent(Content content) {
        this.content = content;
    }

    public CommentDto toDto(ProfileImage profileImage, Nickname nickname, List<ReCommentDto> reCommentDto) {
        return new CommentDto(
                id,
                profileImage.getValue(),
                nickname.getValue(),
                content.getValue(),
                reCommentDto,
                createdAt
        );
    }

    public ReCommentDto toReCommentDto(ProfileImage profileImage, Nickname nickname) {
        return new ReCommentDto(id, profileImage.getValue(), nickname.getValue(), content.getValue(), createdAt);
    }

    public static Comment fake() {
        return new Comment(
                null,
                new PostId(1L),
                new PostType("photo"),
                new Username("test"),
                new Content("댓글은 이렇게 달자")
        );
    }
}
