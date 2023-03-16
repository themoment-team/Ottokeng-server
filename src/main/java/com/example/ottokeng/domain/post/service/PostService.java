package com.example.ottokeng.domain.post.service;

import com.example.ottokeng.domain.post.presentation.dto.request.PostWritingRequest;
import com.example.ottokeng.domain.post.presentation.dto.request.ModifyPostWritingRequest;
import com.example.ottokeng.domain.post.presentation.dto.response.AllPostsResponse;
import com.example.ottokeng.domain.post.presentation.dto.response.RecentPostsResponse;
import com.example.ottokeng.domain.post.presentation.dto.response.ShowPostResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface PostService {
    AllPostsResponse getAllPost();

    void postWritingExecute(PostWritingRequest request, List<String> imgPaths);

    void patchWritingExecute(Long id, ModifyPostWritingRequest request, List<MultipartFile> multipartFiles);

    void deleteWritingExecute(Long id);

    List<ShowPostResponse> search(String keyword);

    RecentPostsResponse recentPost();
}
