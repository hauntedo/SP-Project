package com.technokratos.utils.mapper;

import com.technokratos.model.SkillEntity;
import com.technokratos.service.SkillService;
import org.mapstruct.Mapper;

import java.util.Set;
import java.util.UUID;

@Mapper(componentModel = "spring", uses = SkillService.class)
public interface SkillMapper {


    Set<SkillEntity> toSkillSet(Set<UUID> skills);

}
