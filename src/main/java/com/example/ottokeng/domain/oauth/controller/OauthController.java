package com.example.ottokeng.domain.oauth.controller;

import com.example.ottokeng.domain.oauth.dto.LoginResponse;
import com.example.ottokeng.domain.oauth.service.OauthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
public class OauthController {

    private final OauthService oauthService;

    @GetMapping("/login/oauth/{provider}")
    public ResponseEntity<LoginResponse> login(@PathVariable String provider, @RequestParam String code) {
        LoginResponse loginResponse = oauthService.login(provider, code);
        return new ResponseEntity<>(loginResponse, HttpStatus.OK);
    }

}
