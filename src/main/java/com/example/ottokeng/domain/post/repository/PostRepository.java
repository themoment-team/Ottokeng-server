package com.example.ottokeng.domain.post.repository;

import com.example.ottokeng.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
