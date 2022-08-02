package com.technokratos.service.impl;

import com.technokratos.exception.notfound.SkillNotFoundException;
import com.technokratos.model.SkillEntity;
import com.technokratos.repository.SkillRepository;
import com.technokratos.service.SkillService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class SkillServiceImpl implements SkillService {

    private final SkillRepository skillRepository;

    @Override
    public Set<SkillEntity> getSkillsBySkillIdSet(Set<UUID> skills) {
        return skillRepository.findSkillsByIds(skills);
    }

    @Override
    public SkillEntity getSkill(UUID skillId) {
        return skillRepository.findById(skillId)
                .orElseThrow(SkillNotFoundException::new);
    }


}
