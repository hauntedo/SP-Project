package com.technokratos.repository;

import com.technokratos.model.UniversityYearEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UniversityYearRepository extends JpaRepository<UniversityYearEntity, UUID> {

    Page<UniversityYearEntity> findUniversityYearEntitiesByUniversity_Id(UUID universityId, Pageable pageable);
}
