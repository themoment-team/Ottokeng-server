package com.example.ottokeng.domain.post.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class RecentPostsResponse {
    private final List<RecentPostResponse> recentPosts;
    private final List<ShowPostResponse> recentPostPage;
}
