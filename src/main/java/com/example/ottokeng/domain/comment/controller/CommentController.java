package com.example.ottokeng.domain.comment.controller;

import com.example.ottokeng.domain.comment.dto.AddCommentRequest;
import com.example.ottokeng.domain.comment.dto.ModifyCommentRequest;
import com.example.ottokeng.domain.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post/comment")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Void> commentAdd(@RequestBody AddCommentRequest request) {
        commentService.addComment(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{commentId}")
    public ResponseEntity<Void> commentModify(@PathVariable Long commentId, @RequestBody ModifyCommentRequest request) {
        commentService.modifyComment(commentId, request);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> commentRemove(@PathVariable Long commentId) {
        commentService.removeComment(commentId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
