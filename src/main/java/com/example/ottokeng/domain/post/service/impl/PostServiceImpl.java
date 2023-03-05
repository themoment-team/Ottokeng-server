package com.example.ottokeng.domain.post.service.impl;

import com.example.ottokeng.domain.post.entity.Image;
import com.example.ottokeng.domain.post.entity.Post;
import com.example.ottokeng.domain.post.presentation.dto.request.PostWritingRequest;
import com.example.ottokeng.domain.post.presentation.dto.request.ModifyPostWritingRequest;
import com.example.ottokeng.domain.post.presentation.dto.response.AllPostsResponse;
import com.example.ottokeng.domain.post.presentation.dto.response.ShowPostResponse;
import com.example.ottokeng.domain.post.repository.ImageRepository;
import com.example.ottokeng.domain.post.repository.PostRepository;
import com.example.ottokeng.domain.post.service.PostService;
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
    private final ImageRepository imageRepository;

    @Override
    public AllPostsResponse getAllPost() {
        User user = userUtil.getCurrentUser();

        List<ShowPostResponse> showPostResponses = postRepository.findAll()
                .stream()
                .map(post -> new ShowPostResponse(post,user.getName()))
                .collect(Collectors.toList());
        return AllPostsResponse.builder()
                .list(showPostResponses)
                .build();
    }

    @Override
    public void postWritingExecute(PostWritingRequest request, List<String> imgPaths) {
        if(imgPaths == null || imgPaths.isEmpty()){
            throw new CustomException(ErrorCode.WRONG_INPUT_IMAGE);
        }

        User user = userUtil.getCurrentUser();
        Post post = Post.builder()
                .title(request.getTitle())
                .contents(request.getContents())
                .acquire(request.getAcquire())
                .address(request.getAddress())
                .communication(request.getCommunication())
                .type(request.getType())
                .user(user)
                .build();

        postRepository.save(post);

        for (String imageUrl : imgPaths) {
            Image image = Image.builder()
                    .imageUrl(imageUrl)
                    .post(post)
                    .build();
            imageRepository.save(image);
        }
    }

    @Override
    public void patchWritingExecutte(Long id, ModifyPostWritingRequest request) {
        Post post = postRepository
                .findById(id).orElseThrow(()->
                        new CustomException(ErrorCode.POST_NOT_FOUND));

        post.update(
                request.getTitle(),
                request.getContents(),
                request.getAcquire(),
                request.getAddress(),
                request.getCommunication(),
                request.getType());
    }

    @Override
    public void deleteWritingExecute(Long id) {
        postRepository.deleteById(id);
    }
}
