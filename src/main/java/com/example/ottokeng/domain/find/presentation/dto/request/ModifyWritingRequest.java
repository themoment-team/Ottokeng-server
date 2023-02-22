package com.example.ottokeng.domain.find.presentation.dto.request;

import com.example.ottokeng.domain.find.entity.Acquisition;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ModifyWritingRequest {
    private String title;
    private String detail;
    private String name;
    private Acquisition acquisition;
    private String image;
    private String address;
    private String communication;
}
