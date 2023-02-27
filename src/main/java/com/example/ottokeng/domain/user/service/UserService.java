package com.example.ottokeng.domain.user.service;

import com.example.ottokeng.domain.oauth.entity.RefreshToken;
import com.example.ottokeng.domain.oauth.repository.RefreshTokenRepository;
import com.example.ottokeng.domain.post.presentation.dto.response.ShowPostResponse;
import com.example.ottokeng.domain.user.entity.User;
import com.example.ottokeng.domain.user.repository.UserRepository;
import com.example.ottokeng.global.exception.CustomException;
import com.example.ottokeng.global.security.jwt.JwtTokenProvider;
import com.example.ottokeng.global.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.example.ottokeng.global.exception.ErrorCode.ALREADY_BLACKLIST;
import static com.example.ottokeng.global.exception.ErrorCode.UNABLE_TO_ISSUANCE_REFRESHTOKEN;

@Service
@RequiredArgsConstructor
public class UserService {

    private final CurrentUserUtil currentUserUtil;
    private final RefreshTokenRepository refreshTokenRepository;
    private final RedisTemplate redisTemplate;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserRepository userRepository;

    @Transactional
    public void logout(HttpServletRequest request){
        String accessToken = jwtTokenProvider.resolveToken(request);
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

    @Transactional
    public void delete() {
        User user = currentUserUtil.getCurrentUser();
        userRepository.delete(user);
    }

    @Transactional
    public List<ShowPostResponse> findMyPosts() {
        User currentUser = currentUserUtil.getCurrentUser();

        return currentUser.getPosts()
                .stream()
                .map((post) -> new ShowPostResponse(post, currentUser.getName()))
                .collect(Collectors.toList());
    }
}
