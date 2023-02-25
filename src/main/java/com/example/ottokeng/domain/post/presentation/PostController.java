package com.example.ottokeng.domain.post.presentation;

import com.example.ottokeng.domain.post.presentation.dto.request.PostWritingRequest;
import com.example.ottokeng.domain.post.presentation.dto.request.ModifyPostWritingRequest;
import com.example.ottokeng.domain.post.presentation.dto.response.AllPostsResponse;
import com.example.ottokeng.domain.post.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {
    private final PostService postService;

    @GetMapping("/writing")
    public ResponseEntity<AllPostsResponse> showlost(){
        AllPostsResponse allPost = postService.getAllPost();
        return new ResponseEntity<>(allPost, HttpStatus.OK);
    }

    @PostMapping("/writing")
    public ResponseEntity<Void> postWriting(@RequestBody PostWritingRequest request){
        postService.postWritingExecute(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/writing/{id}")
    public ResponseEntity<Void> patchWriting(@PathVariable Long id, @RequestBody ModifyPostWritingRequest request){
        postService.patchWritingExecutte(id, request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/writing")
    public ResponseEntity<Void> deleteWriting(@RequestParam Long id){
        postService.deleteWritingExecute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
