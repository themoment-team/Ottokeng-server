package com.example.ottokeng.domain.lost.presentation.dto.request;

import com.example.ottokeng.domain.lost.entity.Relay;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ModifyLostWritingRequest {
    private String title;
    private String detail;
    private Relay relay;
    private String image;
    private String address;
    private String communication;
}
