package com.example.ottokeng.domain.comment.service;

import com.example.ottokeng.domain.comment.dto.AddCommentRequest;
import com.example.ottokeng.domain.comment.dto.ModifyCommentRequest;
import com.example.ottokeng.domain.comment.entity.Comment;
import com.example.ottokeng.domain.comment.repository.CommentRepository;
import com.example.ottokeng.domain.post.entity.Post;
import com.example.ottokeng.domain.post.repository.PostRepository;
import com.example.ottokeng.domain.user.entity.User;
import com.example.ottokeng.global.exception.CustomException;
import com.example.ottokeng.global.exception.ErrorCode;
import com.example.ottokeng.global.util.CurrentUserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CurrentUserUtil currentUserUtil;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public void addComment(AddCommentRequest request) {
        User currentUser = currentUserUtil.getCurrentUser();
        Post post = postRepository.findById(request.getPostId())
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        Comment comment = Comment.builder()
                .contents(request.getContents())
                .post(post)
                .user(currentUser)
                .build();

        currentUser.getComments().add(comment);
        post.getComments().add(comment);

        commentRepository.save(comment);
    }

    @Transactional
    public void modifyComment(ModifyCommentRequest request) {
        Comment comment = commentRepository.findById(request.getCommentId())
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));

        comment.modifyContents(request.getContents());
    }
}
