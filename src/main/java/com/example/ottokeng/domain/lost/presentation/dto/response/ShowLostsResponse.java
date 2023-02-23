package com.example.ottokeng.domain.lost.presentation.dto.response;

import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Builder
@RequiredArgsConstructor
public class ShowLostsResponse {
    private final List<ShowLostResponse> list;
}
