package com.example.ottokeng.global.util;

import com.example.ottokeng.global.exception.CustomException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import static com.example.ottokeng.global.exception.ErrorCode.INVALID_TOKEN;

@Component
@RequiredArgsConstructor
public class CurrentUserUtil {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        String oauthId = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(principal instanceof UserDetails) {
            oauthId = ((UserDetails) principal).getUsername();
        } else{
            oauthId = principal.toString();
        }

        return userRepository.findByOauthId(oauthId).orElseThrow(() -> new CustomException(INVALID_TOKEN));
    }

}
