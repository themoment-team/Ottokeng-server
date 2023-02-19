package com.example.ottokeng.domain.user.service;

import com.example.ottokeng.domain.oauth.entity.RefreshToken;
import com.example.ottokeng.domain.oauth.repository.RefreshTokenRepository;
import com.example.ottokeng.domain.user.entity.User;
import com.example.ottokeng.global.exception.CustomException;
import com.example.ottokeng.global.security.jwt.JwtTokenProvider;
import com.example.ottokeng.global.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

import static com.example.ottokeng.global.exception.ErrorCode.ALREADY_BLACKLIST;
import static com.example.ottokeng.global.exception.ErrorCode.UNABLE_TO_ISSUANCE_REFRESHTOKEN;

@Service
@RequiredArgsConstructor
public class UserService {

    private final CurrentUserUtil currentUserUtil;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RedisTemplate redisTemplate;
    private final JwtTokenProvider jwtTokenProvider;

    @Transactional
    public void logout(String accessToken){
        User currentUser = currentUserUtil.getCurrentUser();
        RefreshToken refreshToken = refreshTokenRepository.findById(currentUser.getOauthId())
                .orElseThrow(() -> new CustomException(UNABLE_TO_ISSUANCE_REFRESHTOKEN));
        refreshTokenRepository.delete(refreshToken);
        saveBlackList(currentUser.getOauthId(),accessToken);
    }

    private void saveBlackList(String oauthId, String accessToken){
        if(redisTemplate.opsForValue().get(accessToken)!=null){
            throw new CustomException(ALREADY_BLACKLIST);
        }

        Long expiration = jwtTokenProvider.getExpiration(accessToken);
        redisTemplate.opsForValue().set(accessToken, "access_token", expiration, TimeUnit.MILLISECONDS);
    }
}
