package com.example.ottokeng.domain.find.presentation;

import com.example.ottokeng.domain.find.presentation.dto.request.ModifyWritingRequest;
import com.example.ottokeng.domain.find.presentation.dto.request.WritingRequest;
import com.example.ottokeng.domain.find.presentation.dto.response.ShowFindResponse;
import com.example.ottokeng.domain.find.presentation.dto.response.ShowFindsResponse;
import com.example.ottokeng.domain.find.service.FindWritingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Null;

@Controller
@RequestMapping("/find")
@RequiredArgsConstructor
public class FindController {

    private final FindWritingService findWritingService;

    @GetMapping("/showfind")
    public ResponseEntity<ShowFindsResponse> showfind() {
        ShowFindsResponse showfindsResponse = findWritingService.getFind();
        return new ResponseEntity<>(showfindsResponse, HttpStatus.OK);
    }

    @PostMapping("/writing")
    public ResponseEntity<Void> postWriting(@RequestBody @Valid WritingRequest request) {
        findWritingService.postWritingExecute(request);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/writing/{id}")
    public ResponseEntity<Void> patchWriting(@PathVariable Long id, @RequestBody @Valid ModifyWritingRequest request){
        findWritingService.patchWritingExecute(id, request);
        return new ResponseEntity<>((HttpStatus.NO_CONTENT));
    }

    @DeleteMapping("/writing/{id}")
    public ResponseEntity<Void> deleteWriting(@PathVariable Long id){
        findWritingService.deleteWritingExecute(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
