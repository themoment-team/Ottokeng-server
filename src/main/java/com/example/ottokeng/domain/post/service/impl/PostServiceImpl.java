package com.example.ottokeng.domain.post.service.impl;

import com.example.ottokeng.domain.post.entity.Image;
import com.example.ottokeng.domain.post.entity.Post;
import com.example.ottokeng.domain.post.presentation.dto.request.PostWritingRequest;
import com.example.ottokeng.domain.post.presentation.dto.request.ModifyPostWritingRequest;
import com.example.ottokeng.domain.post.presentation.dto.response.AllPostsResponse;
import com.example.ottokeng.domain.post.presentation.dto.response.RecentPostResponse;
import com.example.ottokeng.domain.post.presentation.dto.response.RecentPostsResponse;
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
import org.springframework.web.multipart.MultipartFile;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {

    private final CurrentUserUtil userUtil;

    private final PostRepository postRepository;
    private final ImageRepository imageRepository;
    private final S3Service s3Service;

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
    public void postWritingExecute(PostWritingRequest request, List<String> imgPaths) {
        if(imgPaths == null || imgPaths.isEmpty()){
            throw new CustomException(ErrorCode.WRONG_INPUT_IMAGE);
        }

        User user = userUtil.getCurrentUser();
        Post post = Post.builder()
                .title(request.getTitle())
                .contents(request.getContents())
                .acquire(request.getAcquire())
                .lat(request.getLat())
                .lng(request.getLng())
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
    public void patchWritingExecute(Long id, ModifyPostWritingRequest request, List<MultipartFile> multipartFiles) {
        Post post = postRepository
                .findById(id).orElseThrow(()->
                        new CustomException(ErrorCode.POST_NOT_FOUND));

        post.update(
                request.getTitle(),
                request.getContents(),
                request.getAcquire(),
                request.getLat(),
                request.getLng(),
                request.getType());

        if(multipartFiles != null) {
            imageRepository.deleteAllByIdInBatch(Collections.singleton(post.getId()));

            List<String> imageUrls = post.getImages().stream()
                    .map(Image::getImageUrl)
                    .collect(Collectors.toList());

            for (String imageUrl: imageUrls) {
                s3Service.deleteS3(imageUrl);
            }

            List<String> imgPaths = s3Service.upload(multipartFiles);

            for (String imageUrl : imgPaths) {
                Image image = Image.builder()
                        .imageUrl(imageUrl)
                        .post(post)
                        .build();
                imageRepository.save(image);
            }
        }
    }

    @Override
    public void deleteWritingExecute(Long id) {
        postRepository.deleteById(id);
    }

    @Override
    public List<ShowPostResponse> search(String keyword) {
        return postRepository.findByTitleContaining(keyword).stream()
                .map(ShowPostResponse::new)
                .collect(Collectors.toList());
    }

    @Override
    public RecentPostsResponse recentPost() {
        List<Post> recentposts = postRepository.findFirst24ByOrderById();
        List<RecentPostResponse> recentPostsResponses = recentposts.stream()
                .map(post -> new RecentPostResponse(
                        post.getUser().getName(),
                        post.getTitle(),
                        post.getImages().get(0).getImageUrl(),
                        post.getCreatedAt()
                )).collect(Collectors.toList());
        List<ShowPostResponse> recentPostPage = postRepository.findFirst24ByOrderById().stream()
                .map(ShowPostResponse::new)
                .collect(Collectors.toList());
        return RecentPostsResponse.builder()
                .recentPosts(recentPostsResponses)
                .recentPostPage(recentPostPage)
                .build();
    }
}
