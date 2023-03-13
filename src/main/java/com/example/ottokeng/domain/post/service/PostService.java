package com.example.ottokeng.domain.post.service;

import com.example.ottokeng.domain.post.presentation.dto.request.PostWritingRequest;
import com.example.ottokeng.domain.post.presentation.dto.request.ModifyPostWritingRequest;
import com.example.ottokeng.domain.post.presentation.dto.response.AllPostsResponse;
import com.example.ottokeng.domain.post.presentation.dto.response.RecentPostResponse;
import com.example.ottokeng.domain.post.presentation.dto.response.ShowPostResponse;

import java.util.List;

public interface PostService {
    AllPostsResponse getAllPost();

    void postWritingExecute(PostWritingRequest request, List<String> imgPaths);

    void patchWritingExecutte(Long id, ModifyPostWritingRequest request, List<String> imgPaths);

    void deleteWritingExecute(Long id);

    void deleteImage(String imageUrl);

    List<ShowPostResponse> search(String keyword);

    List<RecentPostResponse> recentPost();
}
