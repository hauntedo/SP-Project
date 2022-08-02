package com.technokratos.repository;

import com.technokratos.model.UniversityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface UniversityRepository extends JpaRepository<UniversityEntity, UUID>,
        JpaSpecificationExecutor<UniversityEntity> {

    boolean existsByCode(String code);

    Page<UniversityEntity> findUniversityEntitiesByNameContainsIgnoreCase(String name, Pageable pageable);
}
