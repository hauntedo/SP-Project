package com.technokratos.repository;

import com.technokratos.model.AcademyGroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface AcademyGroupRepository extends JpaRepository<AcademyGroupEntity, UUID>,
        JpaSpecificationExecutor<AcademyGroupEntity> {

//    Page<AcademyGroupEntity> findAll(Pageable pageable,
//                                                                      Specification<AcademyGroupEntity> specification);

}
