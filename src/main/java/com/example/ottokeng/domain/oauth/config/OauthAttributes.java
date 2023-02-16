package com.example.ottokeng.domain.oauth.config;

import java.util.Arrays;
import java.util.Map;

public enum OauthAttributes {

    GOOGLE("google") {
        @Override
        public UserProfile of(Map<String, Object> attributes) {
            return UserProfile.builder()
                    .oauthId(String.valueOf(attributes.get("sub")))
                    .name((String) attributes.get("name"))
                    .imageUrl((String) attributes.get("picture"))
                    .build();
        }
    },
    KAKAO("kakao") {
        @Override
        public UserProfile of(Map<String, Object> attributes) {
            Map<String,Object> account = (Map<String, Object>)attributes.get("kakao_account");
            Map<String,Object> profile = (Map<String, Object>) account.get("profile");
            return UserProfile.builder()
                    .oauthId(String.valueOf(attributes.get("id")))
                    .name((String) profile.get("nickname"))
                    .imageUrl((String) profile.get("profile_image_url"))
                    .build();
        }
    };

    private final String providerName;

    OauthAttributes(String name) {
        this.providerName = name;
    }

    public static UserProfile extract(String providerName, Map<String, Object> attributes) {
        return Arrays.stream(values())
                .filter(provider -> providerName.equals(provider.providerName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new)
                .of(attributes);
    }

    public abstract UserProfile of(Map<String, Object> attributes);
}