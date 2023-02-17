package com.example.ottokeng.domain.user.controller;

import com.example.ottokeng.domain.user.service.TokenService;
import com.example.ottokeng.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RequiredArgsConstructor
@RequestMapping
@RestController
public class TokenController {

    private final TokenService tokenService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/token/reissue")
    public ResponseEntity<Map<String, String>> reissueToken(@RequestHeader("RefreshToken") String refreshToken) {
        return ResponseEntity.ok().body(tokenService.reissueToken(jwtTokenProvider.resolveRefreshToken(refreshToken)));
    }

}
