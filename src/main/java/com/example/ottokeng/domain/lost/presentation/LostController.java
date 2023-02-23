package com.example.ottokeng.domain.lost.presentation;

import com.example.ottokeng.domain.lost.presentation.dto.request.LostWritingRequest;
import com.example.ottokeng.domain.lost.presentation.dto.request.ModifyLostWritingRequest;
import com.example.ottokeng.domain.lost.presentation.dto.response.ShowLostsResponse;
import com.example.ottokeng.domain.lost.service.LostWritingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lost")
public class LostController {
    private final LostWritingService lostWritingService;

    @GetMapping("/writing")
    public ResponseEntity<ShowLostsResponse> showlost(){
        ShowLostsResponse showLostsResponse = lostWritingService.getLost();
        return new ResponseEntity<>(showLostsResponse, HttpStatus.OK);
    }

    @PostMapping("/writing")
    public ResponseEntity<Void> postWriting(@RequestBody LostWritingRequest request){
        lostWritingService.postWritingExecute(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/writing")
    public ResponseEntity<Void> patchWriting(@PathVariable Long id, @RequestBody ModifyLostWritingRequest request){
        lostWritingService.patchWritingExecutte(id, request);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/writing")
    public ResponseEntity<Void> deleteWriting(@PathVariable Long id){
        lostWritingService.deleteWritingExecute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
