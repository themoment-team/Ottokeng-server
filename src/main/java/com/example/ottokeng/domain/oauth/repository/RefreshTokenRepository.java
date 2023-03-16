package com.example.ottokeng.domain.oauth.repository;

import com.example.ottokeng.domain.oauth.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;

public interface RefreshTokenRepository extends CrudRepository<RefreshToken, String> {
}
