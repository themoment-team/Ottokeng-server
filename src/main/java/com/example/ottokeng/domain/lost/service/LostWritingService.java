package com.example.ottokeng.domain.lost.service;

import com.example.ottokeng.domain.lost.presentation.dto.request.LostWritingRequest;
import com.example.ottokeng.domain.lost.presentation.dto.request.ModifyLostWritingRequest;
import com.example.ottokeng.domain.lost.presentation.dto.response.ShowLostsResponse;

public interface LostWritingService {
    ShowLostsResponse getLost();

    void postWritingExecute(LostWritingRequest request);

    void patchWritingExecutte(Long id, ModifyLostWritingRequest request);

    void deleteWritingExecute(Long id);
}
