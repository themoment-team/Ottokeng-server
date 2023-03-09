package com.example.ottokeng.domain.post.repository;

import com.example.ottokeng.domain.post.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
    void deleteByImageUrl(String imageUrl);
}
