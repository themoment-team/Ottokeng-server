package com.example.ottokeng.domain.user.repository;

import com.example.ottokeng.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByOauthId(String id);
}
