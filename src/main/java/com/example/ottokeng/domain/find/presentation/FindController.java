package com.example.ottokeng.domain.find.presentation;

import com.example.ottokeng.domain.find.presentation.dto.response.ShowFindResponse;
import com.example.ottokeng.domain.find.service.FindService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lost")
@RequiredArgsConstructor
public class FindController {

    private final FindService findService;

    @GetMapping("/showfind")
    public ResponseEntity<ShowFindResponse> showfind(){
        ShowFindResponse showfindResponse = findService.getFind();
        return new ResponseEntity<>(showfindResponse, HttpStatus.OK);
    }
}
