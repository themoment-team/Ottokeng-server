package com.example.ottokeng.domain.post.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class AllPostsResponse {
    private final List<ShowPostResponse> list;
}
