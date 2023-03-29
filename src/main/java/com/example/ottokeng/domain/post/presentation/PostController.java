package com.example.ottokeng.domain.post.presentation;

import com.example.ottokeng.domain.post.presentation.dto.request.PostWritingRequest;
import com.example.ottokeng.domain.post.presentation.dto.request.ModifyPostWritingRequest;
import com.example.ottokeng.domain.post.presentation.dto.response.AllPostsResponse;
import com.example.ottokeng.domain.post.presentation.dto.response.RecentPostsResponse;
import com.example.ottokeng.domain.post.presentation.dto.response.ShowPostResponse;
import com.example.ottokeng.domain.post.service.PostService;
import com.example.ottokeng.domain.post.service.impl.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@CrossOrigin(origins = {"https://server.ottokeng.site/","https://www.ottokeng.site/","https://ottokeng.site/","http://server.ottokeng.site/","http://www.ottokeng.site/","http://ottokeng.site/"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostService postService;
    private final S3Service s3Service;

    @GetMapping("/writing")
    public ResponseEntity<AllPostsResponse> getAllPost() {
        AllPostsResponse allPost = postService.getAllPost();
        return new ResponseEntity<>(allPost, HttpStatus.OK);
    }

    @GetMapping("/recentPost")
    public ResponseEntity<RecentPostsResponse> recentPost(){
        RecentPostsResponse recentPost = postService.recentPost();
        return new ResponseEntity<>(recentPost, HttpStatus.OK);
    }

    @PostMapping("/writing")
    public ResponseEntity<Void> postWriting(@RequestPart("content") PostWritingRequest request, @RequestPart(value = "file") List<MultipartFile> multipartFiles) {
        List<String> imgPaths = s3Service.upload(multipartFiles);
        postService.postWritingExecute(request, imgPaths);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/writing/{id}")
    public ResponseEntity<Void> patchWriting(@PathVariable Long id, @RequestPart("content") ModifyPostWritingRequest request, @RequestPart(value = "file", required = false) List<MultipartFile> multipartFiles) {
        postService.patchWritingExecute(id, request, multipartFiles);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/writing")
    public ResponseEntity<Void> deleteWriting(@RequestParam Long id) {
        postService.deleteWritingExecute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/writing/search/{keyword}")
    public ResponseEntity<List<ShowPostResponse>> searchPost(@PathVariable String keyword) {
        List<ShowPostResponse> responses = postService.search(keyword);
        return new ResponseEntity<>(responses, HttpStatus.OK);
    }
}