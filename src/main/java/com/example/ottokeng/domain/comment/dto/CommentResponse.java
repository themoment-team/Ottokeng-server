package com.example.ottokeng.domain.comment.dto;

import com.example.ottokeng.domain.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {

    private Long commentId;
    private String writer;
    private String profileImg;
    private String contents;
    private Boolean isMine;
    private LocalDateTime createdAt;

    public CommentResponse(Comment comment, String writerOauthId, String checkOauthId) {
        this.commentId = comment.getId();
        this.writer = comment.getUser().getName();
        this.profileImg = comment.getUser().getImageUrl();
        this.contents = comment.getContents();
        this.createdAt = comment.getCreatedAt();
        this.isMine = Objects.equals(writerOauthId, checkOauthId);
    }
}
