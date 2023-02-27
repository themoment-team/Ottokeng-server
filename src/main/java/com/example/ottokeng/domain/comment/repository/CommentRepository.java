package com.example.ottokeng.domain.comment.repository;

import com.example.ottokeng.domain.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
