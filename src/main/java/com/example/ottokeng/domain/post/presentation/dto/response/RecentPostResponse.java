package com.example.ottokeng.domain.post.presentation.dto.response;

import com.example.ottokeng.domain.post.entity.Get;
import com.example.ottokeng.domain.post.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class RecentPostResponse {
    private Long id;
    private String title;
    private String contents;
    private String name;
    private Get acquire;
    private String image;
    private String address;
    private String communication;
    private Type type;
    private LocalDateTime createdAt;
}
