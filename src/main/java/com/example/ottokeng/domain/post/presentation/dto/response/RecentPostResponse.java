package com.example.ottokeng.domain.post.presentation.dto.response;

import com.example.ottokeng.domain.post.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@AllArgsConstructor
public class RecentPostResponse {
    private String title;
    private String name;
    private String address;
    private String imageUrl;
    private LocalDateTime createdAt;

    public RecentPostResponse(String name, String title, List<Image> imageUrl, String address, LocalDateTime createdAt) {
    }
}
