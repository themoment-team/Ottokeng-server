package com.example.ottokeng.domain.find.service;

import com.example.ottokeng.domain.find.presentation.dto.request.ModifyWritingRequest;
import com.example.ottokeng.domain.find.presentation.dto.request.WritingRequest;
import com.example.ottokeng.domain.find.presentation.dto.response.ShowFindsResponse;

public interface FindWritingService {
    ShowFindsResponse getFind();

    void postWritingExecute(WritingRequest request);

    void patchWritingExecute(Long id, ModifyWritingRequest request);
}
