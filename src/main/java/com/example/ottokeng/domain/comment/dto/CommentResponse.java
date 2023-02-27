package com.example.ottokeng.domain.comment.dto;

import com.example.ottokeng.domain.comment.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentResponse {

    private Long commentId;
    private String writer;
    private String contents;
    private LocalDateTime createdAt;

    public CommentResponse(Comment comment) {
        this.commentId = comment.getId();
        this.writer = comment.getUser().getName();
        this.contents = comment.getContents();
        this.createdAt = comment.getCreatedAt();
    }
}
