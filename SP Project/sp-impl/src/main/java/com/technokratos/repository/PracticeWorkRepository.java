package com.technokratos.repository;

import com.technokratos.model.PracticeWorkEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface PracticeWorkRepository extends JpaRepository<PracticeWorkEntity, UUID> {

    Page<PracticeWorkEntity> findPracticeWorkEntitiesByCourse_Id(UUID course_id, Pageable pageable);

    Optional<PracticeWorkEntity> findPracticeWorkEntityByIdAndCourse_Id(UUID workId, UUID courseId);
}
