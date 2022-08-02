package com.technokratos.repository;

import com.technokratos.model.ConfirmCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ConfirmCodeRepository extends JpaRepository<ConfirmCodeEntity, UUID> {

    Optional<ConfirmCodeEntity> findOneByUserId(UUID userID);
    Optional<ConfirmCodeEntity> findOneByConfirmCode(UUID confirmCode);
}
