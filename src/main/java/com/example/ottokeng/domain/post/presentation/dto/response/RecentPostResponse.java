package com.example.ottokeng.domain.post.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class RecentPostResponse {
    private String title;
    private String name;
    private String address;
    private String imageUrl;
}
