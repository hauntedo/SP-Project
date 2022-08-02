package com.technokratos.repository;

import com.technokratos.model.UserAvatarEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserAvatarRepository extends JpaRepository<UserAvatarEntity, UUID> {

    Optional<UserAvatarEntity> findByUserId(UUID userId);
}
