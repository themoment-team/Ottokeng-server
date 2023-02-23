package com.example.ottokeng.domain.find.presentation.dto.request;

import com.example.ottokeng.domain.find.entity.Acquisition;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@Getter
public class FindWritingRequest {
    private String title;
    private String detail;
    private Acquisition acquisition;
    private String image;
    private String address;
    private String communication;
}
