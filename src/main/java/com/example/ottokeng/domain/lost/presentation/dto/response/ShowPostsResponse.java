package com.example.ottokeng.domain.lost.presentation.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@Builder
@RequiredArgsConstructor
public class ShowPostsResponse {
    private final List<ShowPostResponse> list;
}
