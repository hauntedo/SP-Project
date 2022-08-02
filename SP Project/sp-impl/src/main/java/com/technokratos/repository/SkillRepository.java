package com.technokratos.repository;

import com.technokratos.model.SkillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;
import java.util.UUID;

public interface SkillRepository extends JpaRepository<SkillEntity, UUID> {

    @Query(nativeQuery = true,
            value = "select * from skill where id in :ids")
    Set<SkillEntity> findSkillsByIds(@Param("ids") Iterable<UUID> skillIds);

}
