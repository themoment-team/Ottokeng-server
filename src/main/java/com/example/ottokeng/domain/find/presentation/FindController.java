package com.example.ottokeng.domain.find.presentation;

import com.example.ottokeng.domain.find.presentation.dto.response.ShowFindResponse;
import com.example.ottokeng.domain.find.presentation.dto.response.ShowFindsResponse;
import com.example.ottokeng.domain.find.service.FindWritingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import javax.validation.constraints.Null;

@Controller
@RequestMapping("/find")
@RequiredArgsConstructor
public class FindController {

    private final FindWritingService findWritingService;

    @GetMapping("/showfind")
    public ResponseEntity<ShowFindsResponse> showfind(){
        ShowFindsResponse showfindsResponse = findWritingService.getFind();
        return new ResponseEntity<>(showfindsResponse, HttpStatus.OK);
    }

    @PostMapping("/writing")
    public ResponseEntity<Void> writing(@RequestBody @Valid WritingRequest writingRequest){
        findWritingService.postWritingExecute(writingRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
