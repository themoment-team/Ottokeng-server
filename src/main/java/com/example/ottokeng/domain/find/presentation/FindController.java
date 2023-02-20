package com.example.ottokeng.domain.find.presentation;

import com.example.ottokeng.domain.find.presentation.dto.request.ShowLostRequest;
import com.example.ottokeng.domain.find.presentation.dto.response.ShowLostResponse;
import com.example.ottokeng.domain.find.service.FindService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/lost")
@RequiredArgsConstructor
public class FindController {

    private final FindService findService;

    @GetMapping("/showlost")
    public ResponseEntity<ShowLostResponse> showlost(){
        ShowLostResponse showLostResponse = findService.getLost();
        return new ResponseEntity<>(showLostResponse, HttpStatus.OK);
    }
}
