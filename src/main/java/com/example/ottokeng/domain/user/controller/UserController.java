package com.example.ottokeng.domain.user.controller;

import com.example.ottokeng.domain.user.service.UserService;
import com.example.ottokeng.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
@RequestMapping("/my-page")
public class UserController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @DeleteMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {
        userService.logout(jwtTokenProvider.resolveToken(request));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
