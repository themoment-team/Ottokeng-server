package com.example.ottokeng.domain.oauth.dto;

import com.example.ottokeng.domain.user.entity.User;
import com.example.ottokeng.global.enumType.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserProfile {
    private final String oauthId;
    private final String email;
    private final String name;
    private final String imageUrl;

    @Builder
    public UserProfile(String oauthId, String email, String name, String imageUrl) {
        this.oauthId = oauthId;
        this.email = email;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public User toMember() {
        return User.builder()
                .oauthId(oauthId)
                .name(name)
                .imageUrl(imageUrl)
                .role(Role.ROLE_USER)
                .build();
    }
}