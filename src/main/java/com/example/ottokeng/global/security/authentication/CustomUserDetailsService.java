package com.example.ottokeng.global.security.authentication;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String oauthId) throws UsernameNotFoundException {
        return userRepository.findByOauthId(oauthId)
                .map(CustomUserDetails::new)
                .orElseThrow(() -> new CustomException(USER_NOT_FOUND));
    }

}
