package com.example.ottokeng.domain.comment.service;

import com.example.ottokeng.domain.comment.dto.AddCommentRequest;
import com.example.ottokeng.domain.comment.dto.CommentResponse;
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
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CurrentUserUtil currentUserUtil;
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    @Transactional
    public void addComment(Long postId, AddCommentRequest request) {
        User currentUser = currentUserUtil.getCurrentUser();
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        Comment comment = Comment.builder()
                .contents(request.getContents())
                .post(post)
                .user(currentUser)
                .build();

        commentRepository.save(comment);
    }

    @Transactional
    public void modifyComment(Long commentId, ModifyCommentRequest request) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));

        comment.modifyContents(request.getContents());
    }

    @Transactional
    public void removeComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new CustomException(ErrorCode.COMMENT_NOT_FOUND));

        commentRepository.delete(comment);
    }

    @Transactional
    public List<CommentResponse> findCommentList(Long postId) {
        User user = currentUserUtil.getCurrentUser();
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomException(ErrorCode.POST_NOT_FOUND));

        return post.getComments()
                .stream()
                .map((comment) -> new CommentResponse(comment, comment.getUser().getOauthId(), user.getOauthId()))
                .collect(Collectors.toList());
    }
}
