package com.example.ottokeng.domain.post.presentation.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class RecentPostsResponse {
    private final List<RecentPostResponse> list;
}
