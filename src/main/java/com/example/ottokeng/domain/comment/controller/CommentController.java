package com.example.ottokeng.domain.comment.controller;

import com.example.ottokeng.domain.comment.dto.SaveCommentRequest;
import com.example.ottokeng.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> commentAdd(@RequestBody SaveCommentRequest request) {
        commentService.addComment(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
