package com.example.ottokeng.domain.oauth.service;

import com.example.ottokeng.domain.oauth.config.OauthAttributes;
import com.example.ottokeng.domain.oauth.config.OauthProvider;
import com.example.ottokeng.domain.oauth.dto.LoginResponse;
import com.example.ottokeng.domain.oauth.dto.OauthTokenResponse;
import com.example.ottokeng.domain.oauth.entity.RefreshToken;
import com.example.ottokeng.domain.oauth.dto.UserProfile;
import com.example.ottokeng.domain.oauth.repository.InMemoryProviderRepository;
import com.example.ottokeng.domain.oauth.repository.RefreshTokenRepository;
import com.example.ottokeng.domain.user.entity.User;
import com.example.ottokeng.domain.user.repository.UserRepository;
import com.example.ottokeng.global.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OauthService {

    private final InMemoryProviderRepository inMemoryProviderRepository;
    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenRepository refreshTokenRepository;

    public LoginResponse login(String providerName, String code) {

        OauthProvider provider = inMemoryProviderRepository.findByProviderName(providerName);

        OauthTokenResponse tokenResponse = getToken(code, provider);

        UserProfile userProfile = getUserProfile(providerName, tokenResponse, provider);

        User user = saveOrUpdate(userProfile);

        String accessToken = jwtTokenProvider.createAccessToken(user.getOauthId());
        String refreshToken = jwtTokenProvider.createRefreshToken(user.getOauthId());

        RefreshToken entityToRedis = new RefreshToken(user.getOauthId(), refreshToken, jwtTokenProvider.getREFRESHTOKEN_VALIDATION_EXPIREDTIME());
        refreshTokenRepository.save(entityToRedis);

        return LoginResponse.builder()
                .name(user.getName())
                .imageUrl(user.getImageUrl())
                .role(user.getRole())
                .tokenType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private User saveOrUpdate(UserProfile userProfile) {
        User member = userRepository.findByOauthId(userProfile.getOauthId())
                .map(entity -> entity.update(
                        userProfile.getName(), userProfile.getImageUrl()))
                .orElseGet(userProfile::toMember);
        return userRepository.save(member);
    }

    private OauthTokenResponse getToken(String code, OauthProvider provider) {

        MultiValueMap<String , String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", provider.getClientId() );
        params.add("redirect_uri", provider.getRedirectUrl());
        params.add("code", code);
        params.add("client_secret", provider.getClientSecret());

        return WebClient.create()
                .post()
                .uri(provider.getTokenUrl())
                .body(BodyInserters.fromFormData(params))
                .header("Content-type","application/x-www-form-urlencoded;charset=utf-8" ) //요청 헤더
                .retrieve()
                .bodyToMono(OauthTokenResponse.class)
                .block();

    }

    private MultiValueMap<String, String> tokenRequest(String code, OauthProvider provider) {
        MultiValueMap<String, String> formData = new LinkedMultiValueMap<>();
        formData.add("code", code);
        formData.add("grant_type", "authorization_code");
        formData.add("redirect_uri", provider.getRedirectUrl());
        return formData;
    }

    private UserProfile getUserProfile(String providerName, OauthTokenResponse tokenResponse, OauthProvider provider) {
        Map<String, Object> userAttributes = getUserAttributes(provider, tokenResponse);
        return OauthAttributes.extract(providerName, userAttributes);
    }

    private Map<String, Object> getUserAttributes(OauthProvider provider, OauthTokenResponse tokenResponse) {
        return WebClient.create()
                .get()
                .uri(provider.getUserInfoUrl())
                .headers(header -> header.setBearerAuth(tokenResponse.getAccessToken()))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .block();
    }

}
