package com.example.ottokeng.domain.user.service;

import com.example.ottokeng.domain.oauth.dto.RefreshToken;
import com.example.ottokeng.domain.oauth.repository.RefreshTokenRepository;
import com.example.ottokeng.domain.user.repository.UserRepository;
import com.example.ottokeng.global.exception.CustomException;
import com.example.ottokeng.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

import static com.example.ottokeng.global.exception.ErrorCode.UNABLE_TO_ISSUANCE_REFRESHTOKEN;

@Service
@RequiredArgsConstructor
public class TokenService {

    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    @Transactional
    public Map<String, String> reissueToken(String reqRefreshToken) {
        String oauthId = jwtTokenProvider.getOAuthId(reqRefreshToken);

        RefreshToken refreshToken = refreshTokenRepository.findById(oauthId)
                .orElseThrow(() -> new CustomException(UNABLE_TO_ISSUANCE_REFRESHTOKEN));

        if(!refreshToken.getRefreshToken().equals(reqRefreshToken) && jwtTokenProvider.validateToken(reqRefreshToken)) {
            throw new CustomException(UNABLE_TO_ISSUANCE_REFRESHTOKEN);
        }

        Map<String, String> map = new HashMap<>();

        String newAccessToken = jwtTokenProvider.createAccessToken(oauthId);
        String newRefreshToken = jwtTokenProvider.createRefreshToken(oauthId);

        refreshToken.exchangeRefreshToken(newRefreshToken);
        refreshTokenRepository.save(refreshToken);

        map.put("AccessToken", "Bearer " + newAccessToken);
        map.put("RefreshToken", "Bearer " + newRefreshToken);

        return map;
    }

}
