package com.example.ottokeng.domain.find.service.impl;

import com.example.ottokeng.domain.find.presentation.dto.response.ShowFindResponse;
import com.example.ottokeng.domain.find.presentation.dto.response.ShowFindsResponse;
import com.example.ottokeng.domain.find.service.FindWritingService;
import com.example.ottokeng.domain.user.entity.User;
import com.example.ottokeng.global.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FindWritingServiceImpl implements FindWritingService {

    private final CurrentUserUtil userUtil;

    private final FindWritingRepsitory findWritingRepsitory;

    @Override
    public ShowFindsResponse getFind() {
        User user = userUtil.getCurrentUser();
        List<ShowFindResponse> showFindResponse = user.getFind().getFindWriting()
                .stream()
                .map(ShowFindResponse::new)
                .collect(Collectors.toList());
        return ShowFindsResponse.builder()
                .list(showFindResponse)
                .build();
    }
}
