package com.example.ottokeng.domain.find.presentation.dto.response;

import com.example.ottokeng.domain.find.entity.Find;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Builder
@RequiredArgsConstructor
public class ShowFindsResponse {
    private final List<ShowFindResponse> list;
}
