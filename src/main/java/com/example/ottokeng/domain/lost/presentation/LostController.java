package com.example.ottokeng.domain.lost.presentation;

import com.example.ottokeng.domain.lost.presentation.dto.request.PostWritingRequest;
import com.example.ottokeng.domain.lost.presentation.dto.request.ModifyPostWritingRequest;
import com.example.ottokeng.domain.lost.presentation.dto.response.AllPostsResponse;
import com.example.ottokeng.domain.lost.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class LostController {
    private final PostService lostWritingService;

    @GetMapping("/writing")
    public ResponseEntity<AllPostsResponse> showlost(){
        AllPostsResponse showLostsResponse = lostWritingService.getAllPost();
        return new ResponseEntity<>(showLostsResponse, HttpStatus.OK);
    }

    @PostMapping("/writing")
    public ResponseEntity<Void> postWriting(@RequestBody PostWritingRequest request){
        lostWritingService.postWritingExecute(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/writing/{id}")
    public ResponseEntity<Void> patchWriting(@PathVariable Long id, @RequestBody ModifyPostWritingRequest request){
        lostWritingService.patchWritingExecutte(id, request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/writing")
    public ResponseEntity<Void> deleteWriting(@RequestParam Long id){
        lostWritingService.deleteWritingExecute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
