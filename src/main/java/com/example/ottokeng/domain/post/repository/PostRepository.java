package com.example.ottokeng.domain.post.repository;

import com.example.ottokeng.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findByTitleContaining(String keyword);

    List<Post> findFirst24ByOrderById();
}
