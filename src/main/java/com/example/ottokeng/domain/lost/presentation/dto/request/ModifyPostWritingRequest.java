package com.example.ottokeng.domain.lost.presentation.dto.request;

import com.example.ottokeng.domain.lost.entity.Get;
import com.example.ottokeng.domain.lost.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ModifyPostWritingRequest {
    private String title;
    private String contents;
    private String name;
    private String date;
    private Get get;
    private String image;
    private String address;
    private String communication;
    private Type type;
}
