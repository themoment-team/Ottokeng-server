package com.example.ottokeng.domain.lost.repository;

import com.example.ottokeng.domain.lost.entity.LostWriting;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LostWritingRepository extends JpaRepository<LostWriting, Long> {
}
