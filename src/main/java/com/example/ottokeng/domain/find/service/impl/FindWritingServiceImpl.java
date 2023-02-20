package com.example.ottokeng.domain.find.service.impl;

import com.example.ottokeng.domain.find.presentation.dto.response.ShowFindResponse;
import com.example.ottokeng.domain.find.presentation.dto.response.ShowFindsResponse;
import com.example.ottokeng.domain.find.service.FindWritingService;
import com.example.ottokeng.domain.user.entity.User;
import com.example.ottokeng.global.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindWritingServiceImpl implements FindWritingService {

    private final CurrentUserUtil userUtil;

    private final

    @Override
    public ShowFindResponse getFind() {
        User user = userUtil.getCurrentUser();
        ShowFindsResponse showFindsResponse
    }
}
