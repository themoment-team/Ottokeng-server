package com.example.ottokeng.domain.find.service;

import com.example.ottokeng.domain.find.presentation.dto.request.WritingRequest;
import com.example.ottokeng.domain.find.presentation.dto.response.ShowFindsResponse;

public interface FindWritingService {
    ShowFindsResponse getFind();

    void postWritingExecute(WritingRequest request);
}
