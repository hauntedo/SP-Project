package com.technokratos.utils.mapper;

import com.technokratos.dto.response.UniversityYearResponse;
import com.technokratos.model.UniversityYearEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UniversityYearMapper {

    UniversityYearResponse toResponse(UniversityYearEntity universityYearEntity);

    List<UniversityYearResponse> toList(List<UniversityYearEntity> universityYearEntityList);
}
