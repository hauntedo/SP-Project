package com.technokratos.utils.mapper;

import com.technokratos.dto.response.AcademyGroupResponse;
import com.technokratos.model.AcademyGroupEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AcademyGroupMapper {

    AcademyGroupResponse toAcademyGroupResponse(AcademyGroupEntity academyGroupEntity);

    List<AcademyGroupResponse> toList(List<AcademyGroupEntity> academyGroupEntityList);
}
