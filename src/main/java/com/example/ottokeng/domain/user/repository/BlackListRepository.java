package com.example.ottokeng.domain.user.repository;

import com.example.ottokeng.domain.user.entity.BlackList;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlackListRepository extends JpaRepository<BlackList,String> {
}
