package com.example.ottokeng.domain.lost.repository;

import com.example.ottokeng.domain.lost.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
