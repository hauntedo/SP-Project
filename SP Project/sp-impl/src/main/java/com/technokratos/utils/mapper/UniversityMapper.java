package com.technokratos.utils.mapper;

import com.technokratos.dto.request.UniversityRequest;
import com.technokratos.dto.response.UniversityResponse;
import com.technokratos.model.UniversityEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UniversityMapper {

    UniversityResponse toUniversityResponse(UniversityEntity universityEntity);

    UniversityEntity toUniversityEntity(UniversityRequest universityRequest);

    void updateUniversity(@MappingTarget UniversityEntity universityEntity, UniversityRequest universityRequest);

    List<UniversityResponse> toList(List<UniversityEntity> universities);
}
