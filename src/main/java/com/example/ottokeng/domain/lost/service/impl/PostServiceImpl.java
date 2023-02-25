package com.example.ottokeng.domain.lost.service.impl;

import com.example.ottokeng.domain.lost.entity.Post;
import com.example.ottokeng.domain.lost.presentation.dto.request.PostWritingRequest;
import com.example.ottokeng.domain.lost.presentation.dto.request.ModifyPostWritingRequest;
import com.example.ottokeng.domain.lost.presentation.dto.response.AllPostsResponse;
import com.example.ottokeng.domain.lost.presentation.dto.response.ShowPostResponse;
import com.example.ottokeng.domain.lost.repository.PostRepository;
import com.example.ottokeng.domain.lost.service.PostService;
import com.example.ottokeng.domain.user.entity.User;
import com.example.ottokeng.domain.user.repository.UserRepository;
import com.example.ottokeng.global.exception.CustomException;
import com.example.ottokeng.global.exception.ErrorCode;
import com.example.ottokeng.global.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {

    private final CurrentUserUtil userUtil;

    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public AllPostsResponse getAllPost() {
        List<ShowPostResponse> showPostResponses = postRepository.findAll()
                .stream()
                .map(ShowPostResponse::new)
                .collect(Collectors.toList());
        return AllPostsResponse.builder()
                .list(showPostResponses)
                .build();
    }

    @Override
    public void postWritingExecute(PostWritingRequest request) {
        User user = userUtil.getCurrentUser();
        Post lostWriting = Post.builder()
                .title(request.getTitle())
                .contents(request.getContents())
                .date(request.getDate())
                .image(request.getImage())
                .acquire(request.getAcquire())
                .address(request.getAddress())
                .communication(request.getCommunication())
                .type(request.getType())
                .user(user)
                .build();

        postRepository.save(lostWriting);
    }

    @Override
    public void patchWritingExecutte(Long id, ModifyPostWritingRequest request) {
        Post lostWriting = postRepository
                .findById(id).orElseThrow(()->
                        new CustomException(ErrorCode.LOST_WRITING_NOT_FOUND));

        lostWriting.update(
                request.getTitle(),
                request.getContents(),
                request.getDate(),
                request.getAcquire(),
                request.getImage(),
                request.getAddress(),
                request.getCommunication(),
                request.getType());
    }

    @Override
    public void deleteWritingExecute(Long id) {
        postRepository.deleteById(id);
    }
}
