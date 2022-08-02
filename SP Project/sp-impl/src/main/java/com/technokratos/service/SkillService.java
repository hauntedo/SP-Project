package com.technokratos.service;

import com.technokratos.model.SkillEntity;

import java.util.Set;
import java.util.UUID;

public interface SkillService {

    Set<SkillEntity> getSkillsBySkillIdSet(Set<UUID> skills);

    SkillEntity getSkill(UUID skillId);
}
