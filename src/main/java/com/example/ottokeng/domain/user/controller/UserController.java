package com.example.ottokeng.domain.user.controller;

import com.example.ottokeng.domain.comment.dto.CommentResponse;
import com.example.ottokeng.domain.post.presentation.dto.response.ShowPostResponse;
import com.example.ottokeng.domain.user.service.UserService;
import com.example.ottokeng.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/my-page")
public class UserController {

    private final UserService userService;

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        userService.logout(request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> delete() {
        userService.delete();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/post")
    public ResponseEntity<List<ShowPostResponse>> myPostList() {
        List<ShowPostResponse> myPosts = userService.findMyPosts();
        return new ResponseEntity<>(myPosts, HttpStatus.OK);
    }

    @GetMapping("/comment")
    public ResponseEntity<List<ShowPostResponse>> myCommentList() {
        List<ShowPostResponse> postResponses = userService.findMyComments();
        return new ResponseEntity<>(postResponses, HttpStatus.OK);
    }

}
