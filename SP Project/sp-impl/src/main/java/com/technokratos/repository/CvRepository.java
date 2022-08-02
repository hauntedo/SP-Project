package com.technokratos.repository;

import com.technokratos.model.CvEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CvRepository extends JpaRepository<CvEntity, UUID> {

    Optional<CvEntity> findByUserId(UUID userId);
}
