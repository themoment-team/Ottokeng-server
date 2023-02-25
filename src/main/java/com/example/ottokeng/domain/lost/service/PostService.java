package com.example.ottokeng.domain.lost.service;

import com.example.ottokeng.domain.lost.presentation.dto.request.PostWritingRequest;
import com.example.ottokeng.domain.lost.presentation.dto.request.ModifyPostWritingRequest;
import com.example.ottokeng.domain.lost.presentation.dto.response.AllPostsResponse;

public interface PostService {
    AllPostsResponse getAllPost();

    void postWritingExecute(PostWritingRequest request);

    void patchWritingExecutte(Long id, ModifyPostWritingRequest request);

    void deleteWritingExecute(Long id);
}
