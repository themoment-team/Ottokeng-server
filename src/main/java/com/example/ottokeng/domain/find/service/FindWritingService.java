package com.example.ottokeng.domain.find.service;

import com.example.ottokeng.domain.find.presentation.dto.request.ModifyWritingRequest;
import com.example.ottokeng.domain.find.presentation.dto.request.FindWritingRequest;
import com.example.ottokeng.domain.find.presentation.dto.response.ShowFindsResponse;

public interface FindWritingService {
    ShowFindsResponse getFind();

    void postWritingExecute(FindWritingRequest request);

    void patchWritingExecute(Long id, ModifyWritingRequest request);

    void deleteWritingExecute(Long id);
}
