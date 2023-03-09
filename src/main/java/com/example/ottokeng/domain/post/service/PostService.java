package com.example.ottokeng.domain.post.service;

import com.example.ottokeng.domain.post.presentation.dto.request.PostWritingRequest;
import com.example.ottokeng.domain.post.presentation.dto.request.ModifyPostWritingRequest;
import com.example.ottokeng.domain.post.presentation.dto.response.AllPostsResponse;
import com.example.ottokeng.domain.post.presentation.dto.response.RecentPostResponse;

import java.util.List;

public interface PostService {
    AllPostsResponse getAllPost();

    List<RecentPostResponse> getRecentPosts();

    void postWritingExecute(PostWritingRequest request);

    void patchWritingExecutte(Long id, ModifyPostWritingRequest request);

    void deleteWritingExecute(Long id);

}
