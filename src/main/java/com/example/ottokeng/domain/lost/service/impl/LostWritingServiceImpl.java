package com.example.ottokeng.domain.lost.service.impl;

import com.example.ottokeng.domain.lost.entity.Post;
import com.example.ottokeng.domain.lost.presentation.dto.request.PostWritingRequest;
import com.example.ottokeng.domain.lost.presentation.dto.request.ModifyPostWritingRequest;
import com.example.ottokeng.domain.lost.presentation.dto.response.ShowPostResponse;
import com.example.ottokeng.domain.lost.presentation.dto.response.ShowPostsResponse;
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
    public ShowPostsResponse getLost() {
        User user = userUtil.getCurrentUser();
        List<ShowPostResponse> showLostResponses = user.getLostWriting()
                .stream()
                .map(ShowPostResponse::new)
                .collect(Collectors.toList());
        return ShowPostsResponse.builder()
                .list(showLostResponses)
                .build();
    }

    @Override
    public void postWritingExecute(PostWritingRequest request) {
        User user = userUtil.getCurrentUser();
        Post lostWriting = Post.builder()
                .title(request.getTitle())
                .contents(request.getContents())
                .name(user.getName())
                .date(request.getDate())
                .image(request.getImage())
                .get(request.getGet())
                .address(request.getAddress())
                .communication(request.getCommunication())
                .type(request.getType())
                .user(userUtil.getCurrentUser())
                .build();

        lostWritingRepository.save(lostWriting);
    }

    @Override
    public void patchWritingExecutte(Long id, ModifyPostWritingRequest request) {
        Post lostWriting = lostWritingRepository
                .findById(id).orElseThrow(()->
                        new CustomException(ErrorCode.LOST_WRITING_NOT_FOUND));

        lostWriting.update(
                request.getTitle(),
                request.getContents(),
                request.getDate(),
                request.getGet(),
                request.getImage(),
                request.getAddress(),
                request.getCommunication(),
                request.getType());
    }

    @Override
    public void deleteWritingExecute(Long id) {
        lostWritingRepository.deleteById(id);
    }
}
