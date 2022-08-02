package com.technokratos.repository;

import com.technokratos.model.CourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface CourseRepository extends JpaRepository<CourseEntity, UUID>, JpaSpecificationExecutor<CourseEntity> {

    @Query(nativeQuery = true,
    value = "select * from course order by id",
    countQuery = "select count(*) from course")
    Page<CourseEntity> findAllCourses(Pageable pageable);

    boolean existsCourseEntityByCode(String code);

    Page<CourseEntity> findByUsers_Id(UUID userId, Pageable pageable);

    Page<CourseEntity> findCourseEntitiesByUniversity_Id(UUID universityId, Pageable pageable);
}
