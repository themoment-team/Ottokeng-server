package com.example.ottokeng.domain.find.presentation.dto.request;

import com.example.ottokeng.domain.find.entity.Acquisition;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class WritingRequest {
    private String title;
    private String detail;
    private Acquisition acquisition;
    private String image;
    private String address;
    private String communication;
}
