package com.example.ottokeng.domain.oauth.repository;

import com.example.ottokeng.domain.oauth.dto.RefreshToken;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
