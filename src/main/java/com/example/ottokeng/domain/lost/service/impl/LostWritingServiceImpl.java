package com.example.ottokeng.domain.lost.service.impl;

import com.example.ottokeng.domain.lost.entity.LostWriting;
import com.example.ottokeng.domain.lost.presentation.dto.request.LostWritingRequest;
import com.example.ottokeng.domain.lost.presentation.dto.request.ModifyLostWritingRequest;
import com.example.ottokeng.domain.lost.presentation.dto.response.ShowLostResponse;
import com.example.ottokeng.domain.lost.presentation.dto.response.ShowLostsResponse;
import com.example.ottokeng.domain.lost.repository.LostWritingRepository;
import com.example.ottokeng.domain.lost.service.LostWritingService;
import com.example.ottokeng.domain.user.entity.User;
import com.example.ottokeng.global.exception.CustomException;
import com.example.ottokeng.global.exception.ErrorCode;
import com.example.ottokeng.global.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class LostWritingServiceImpl implements LostWritingService {

    private final CurrentUserUtil userUtil;

    private final LostWritingRepository lostWritingRepository;

    @Override
    public ShowLostsResponse getLost() {
        User user = userUtil.getCurrentUser();
        List<ShowLostResponse> showLostResponses = user.getLost().getLostWriting()
                .stream()
                .map(ShowLostResponse::new)
                .collect(Collectors.toList());
        return ShowLostsResponse.builder()
                .list(showLostResponses)
                .build();
    }

    @Override
    public void postWritingExecute(LostWritingRequest request) {
        LostWriting lostWriting = LostWriting.builder()
                .title(request.getTitle())
                .detail(request.getDetail())
                .image(request.getImage())
                .relay(request.getRelay())
                .address(request.getAddress())
                .communication(request.getCommunication())
                .build();

        lostWritingRepository.save(lostWriting);
    }

    @Override
    public void patchWritingExecutte(Long id, ModifyLostWritingRequest request) {
        LostWriting lostWriting = lostWritingRepository
                .findById(id).orElseThrow(()->
                        new CustomException(ErrorCode.LOST_WRITING_NOT_FOUND));

        lostWriting.update(
                request.getTitle(),
                request.getDetail(),
                request.getRelay(),
                request.getImage(),
                request.getAddress(),
                request.getCommunication());
    }

    @Override
    public void deleteWritingExecute(Long id) {
        lostWritingRepository.deleteById(id);
    }
}
