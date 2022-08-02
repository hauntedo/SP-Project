package com.technokratos.repository;

import com.technokratos.model.RefreshTokenEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRefreshTokenRepository extends JpaRepository<RefreshTokenEntity, UUID> {
}
