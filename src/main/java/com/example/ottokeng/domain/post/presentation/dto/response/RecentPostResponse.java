package com.example.ottokeng.domain.post.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class RecentPostResponse {
    private Long id;
    private String title;
    private String image;
    private LocalDateTime createdAt;
}
