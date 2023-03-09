package com.example.ottokeng.domain.post.presentation.dto.request;

import com.example.ottokeng.domain.post.entity.Get;
import com.example.ottokeng.domain.post.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostWritingRequest {
    private String title;
    private String contents;
    private Get acquire;
    private String address;
    private Type type;
}
