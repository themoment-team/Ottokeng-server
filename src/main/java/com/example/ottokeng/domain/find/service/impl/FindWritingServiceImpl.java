package com.example.ottokeng.domain.find.service.impl;

import com.example.ottokeng.domain.find.entity.FindWriting;
import com.example.ottokeng.domain.find.exceptions.FindWritingNotFoundException;
import com.example.ottokeng.domain.find.presentation.dto.request.ModifyWritingRequest;
import com.example.ottokeng.domain.find.presentation.dto.request.WritingRequest;
import com.example.ottokeng.domain.find.presentation.dto.response.ShowFindResponse;
import com.example.ottokeng.domain.find.presentation.dto.response.ShowFindsResponse;
import com.example.ottokeng.domain.find.repository.FindWritingRepository;
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

    private final FindWritingRepository findWritingRepsitory;

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

    @Override
    public void postWritingExecute(WritingRequest writingRequest) {
        FindWriting findWriting = FindWriting.builder()
                .title(writingRequest.getTitle())
                .detail(writingRequest.getDetail())
                .acquisition(writingRequest.getAcquisition())
                .address(writingRequest.getAddress())
                .image(writingRequest.getImage())
                .communication(writingRequest.getCommunication())
                .build();

        findWritingRepsitory.save(findWriting);
    }

    @Override
    public void patchWritingExecute(Long id, ModifyWritingRequest request) {
        FindWriting findWriting = findWritingRepsitory
                .findById(id).orElseThrow(()->
                        new FindWritingNotFoundException("존재하지 않는 찾기 글입니다."));
        findWriting.update(
                request.getTitle(),
                request.getDetail(),
                request.getAcquisition(),
                request.getImage(),
                request.getAddress(),
                request.getCommunication());
    }

    @Override
    public void deleteWritingExecute(Long id) {
        findWritingRepsitory.deleteById(id);
    }
}
